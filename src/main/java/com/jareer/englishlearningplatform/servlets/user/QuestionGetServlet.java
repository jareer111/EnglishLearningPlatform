package com.jareer.englishlearningplatform.servlets.user;

import com.google.gson.Gson;
import com.jareer.englishlearningplatform.domains.Questions;
import com.jareer.englishlearningplatform.domains.QuizHelper;
import com.jareer.englishlearningplatform.domains.Variants;
import com.jareer.englishlearningplatform.service.TeacherService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "QuestionGetServlet", value = "/questions/get/*")
public class QuestionGetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TeacherService teacherService =TeacherService.getInstance();
        String pathInfo = request.getPathInfo();
        int grammarId = Integer.parseInt(pathInfo.substring(1));
        System.out.println(grammarId);
        try {
            int id = Integer.parseInt(String.valueOf(grammarId));
            List<QuizHelper> quizHelperList = new ArrayList<>();
            List<Questions> questionsList = teacherService.findAllByGrammarId(id);
            for (int i = 0; i <questionsList.size() ; i++) {
                List<Variants> variants = teacherService.findAllByQuestionId(questionsList.get(i).getId());
                QuizHelper quiz = QuizHelper.builder().
                        question(questionsList.get(i).getTitle()).
                        questionId(questionsList.get(i).getId()).
                        a(variants.get(0).getVariant()).
                        b(variants.get(1).getVariant()).
                        c(variants.get(2).getVariant()).
                        d(variants.get(3).getVariant()).
                        correct(getCorrectValue(variants)).build();
                quizHelperList.add(quiz);
                System.out.println(quiz);
            }
            Gson gson = new Gson();
            String jsonData = gson.toJson(quizHelperList);
            System.out.println(quizHelperList);
            response.setContentType("application/json");
            response.getWriter().println(jsonData);
        } catch (NumberFormatException e) {
            System.out.println("Number format exception!");
        }
    }

    private String getCorrectValue(List<Variants> variants) {
        if (variants.size() != 4) {
            return "";
        }
        if (variants.get(0).isCorrect()) {
            return "a";
        }
        if (variants.get(1).isCorrect()) {
            return "b";
        }
        if (variants.get(2).isCorrect()) {
            return "c";
        }
        if (variants.get(3).isCorrect()) {
            return "d";
        }
        return "";
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
