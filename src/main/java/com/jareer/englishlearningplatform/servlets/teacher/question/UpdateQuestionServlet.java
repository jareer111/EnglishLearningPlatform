package com.jareer.englishlearningplatform.servlets.teacher.question;

import com.jareer.englishlearningplatform.domains.Questions;
import com.jareer.englishlearningplatform.domains.Variants;
import com.jareer.englishlearningplatform.service.TeacherService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "UpdateQuestionServlet", value = "/teacher/grammar/update-question/*")
public class UpdateQuestionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String questionId = request.getPathInfo().substring(1);
        try {
            TeacherService teacherService = TeacherService.getInstance();
            int id = Integer.parseInt(questionId);
            Questions questions=teacherService.getQuestionById(id);
            if (Objects.isNull(questions)){
                throw new Exception("Question not found");
            }
            List<Variants> options = teacherService.getVariantsByQuestionId(id);
            if (options.size() != 4){
                throw new Exception("Variants not found");
            }
            request.setAttribute("question", questions);
            request.setAttribute("option1", options.get(0));
            request.setAttribute("option2", options.get(1));
            request.setAttribute("option3", options.get(2));
            request.setAttribute("option4", options.get(3));
            request.setAttribute("correct", (options.get(0).isCorrect() ? 1 : options.get(1).isCorrect() ? 2 : options.get(2).isCorrect() ? 3 : 4));

            request.getRequestDispatcher("/views/teacher/grammar/update-question.jsp").forward(request, response);
        }catch (Exception e){
            response.sendRedirect("/teacher/grammar/update/"+questionId);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String questionId = request.getPathInfo().substring(1);
        try {
            TeacherService teacherService = TeacherService.getInstance();

            String option1 = request.getParameter("answer1");
            String option2 = request.getParameter("answer2");
            String option3 = request.getParameter("answer3");
            String option4 = request.getParameter("answer4");
            String correctAnswer = request.getParameter("correctAnswer");
            String newTitle = request.getParameter("question");


            Questions question = teacherService.getQuestionById(Integer.parseInt(questionId));
//            question.setGrammar(grammar);
            question.setTitle(Objects.requireNonNullElse(newTitle, question.getTitle()));

            TeacherService.getInstance().updateQuestion(question);
            deleteQuestionOptions(question);
            updateQuestionOptions(question, correctAnswer, option1, option2, option3, option4);
            response.sendRedirect("/teacher/grammar/update-question/"+questionId)   ;
        }catch (Exception e){
            response.sendRedirect("/teacher/grammar/list");
        }
    }
    private void deleteQuestionOptions(Questions questions) {
        TeacherService.getInstance().deleteVariantsByQuestionId(questions.getId());
    }

    private void updateQuestionOptions(Questions questions, String correctOption, String... options) {
        for (int i = 0; i < options.length; i++) {
            Variants variants = new Variants();
            variants.setQuestions(questions);
            variants.setVariant(options[i]);
            variants.setCorrect(String.valueOf(i + 1).equals(correctOption));
            TeacherService.getInstance().saveVariant(variants);
        }
    }
}
