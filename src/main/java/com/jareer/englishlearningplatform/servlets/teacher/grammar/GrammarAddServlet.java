package com.jareer.englishlearningplatform.servlets.teacher.grammar;


import com.jareer.englishlearningplatform.domains.Document;
import com.jareer.englishlearningplatform.domains.Grammar;
import com.jareer.englishlearningplatform.domains.Questions;
import com.jareer.englishlearningplatform.domains.Variants;
import com.jareer.englishlearningplatform.enums.Levels;
import com.jareer.englishlearningplatform.service.TeacherService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "GrammarAddServlet", value = "/teacher/grammar/add")
@MultipartConfig(location = "/home/javohir/apps/elearn/upload/")
public class GrammarAddServlet extends HttpServlet {
    public static final String projectPath = System.getProperty("user.dir");
    private static final Path rootPath = Path.of("/home/javohir/apps/elearn/upload/");

    @Override
    public void init() throws ServletException {
        if (!Files.exists(rootPath)) {
            try {
                Files.createDirectories(rootPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Levels> levels = List.of(Levels.values());
        request.setAttribute("levels", levels);
        request.getRequestDispatcher("/views/teacher/grammar/add.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String score = request.getParameter("score");
        String level = request.getParameter("level");
        TeacherService teacherService = TeacherService.getInstance();
        Integer userId = Integer.parseInt(request.getSession().getAttribute("user_id").toString());

        Part file = request.getPart("file");
        String originalName = file.getSubmittedFileName();
        String extension = originalName.substring(originalName.lastIndexOf("."));
        String generatedName = UUID.randomUUID() + extension;
        Document document = Document.builder()
                .generatedFileName(generatedName)
                .originalFileName(originalName)
                .filePath("uploads/files/stories/" + generatedName)
                .createdBy(userId) // TODO: 2/16/2023 admin id ni qo'shish kerak  qushdim
                .build();
        file.write(generatedName);
        Grammar grammar = Grammar.builder()
                .title(title)
                .score(Integer.parseInt(score))
                .level(Levels.valueOf(level))
                .document(document)
                .createdBy(userId) // TODO: 2/16/2023 admin id ni qo'shish kerak  qushdim
                .build();
        teacherService.saveGrammar(grammar);

        String option1 = request.getParameter("option1");
        String option2 = request.getParameter("option2");
        String option3 = request.getParameter("option3");
        String option4 = request.getParameter("option4");
        String correctAnswer = request.getParameter("correctAnswer");
        String question = request.getParameter("question");

        Questions questions = Questions.builder()
                .grammar(grammar)
                .title(question)
                .build();

        TeacherService.getInstance().saveQuestion(questions);

        saveQuestionOptions(questions, correctAnswer, option1, option2, option3, option4);
        response.sendRedirect("/teacher/grammar/list");
    }

    private void saveQuestionOptions(Questions questions, String correctOption, String... options) {
        for (int i = 0; i < options.length; i++) {
            Variants variants = new Variants();
            variants.setQuestions(questions);
            variants.setVariant(options[i]);
            variants.setCorrect(String.valueOf(i + 1).equals(correctOption));
            TeacherService.getInstance().saveVariant(variants);
        }
    }
}

