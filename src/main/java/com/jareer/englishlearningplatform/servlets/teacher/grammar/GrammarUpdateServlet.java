package com.jareer.englishlearningplatform.servlets.teacher.grammar;

import com.jareer.englishlearningplatform.domains.Document;
import com.jareer.englishlearningplatform.domains.Grammar;
import com.jareer.englishlearningplatform.domains.Questions;
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
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@WebServlet(name = "GrammarUpdateServlet", value = "/teacher/grammar/update/*")
@MultipartConfig(location = "c:\\E-Learn\\uploads\\grammars")
public class GrammarUpdateServlet extends HttpServlet {
    private static final Path rootPath = Path.of(System.getProperty("user.home"), "/E-Learn/uploads/grammars");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String grammarId = request.getPathInfo().substring(1);
        try {
            int id = Integer.parseInt(grammarId);
            Grammar grammar = TeacherService.getInstance().getGrammarById(id);

            TeacherService teacherService = TeacherService.getInstance();
            List<Questions> questions = teacherService.getGrammarQuestions(grammar);
            request.setAttribute("questions", questions);

            request.setAttribute("grammar", grammar);
            List<Levels> levels = List.of(Levels.values());
            request.setAttribute("levels", levels);
            request.getRequestDispatcher("/views/teacher/grammar/update.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendRedirect("/teacher/grammar");
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String grammarId = request.getPathInfo().substring(1);
        int id = Integer.parseInt(grammarId);
        TeacherService teacherService = TeacherService.getInstance();
        Grammar grammar = teacherService.getGrammarById(id);

        String title = request.getParameter("title");
        String score = request.getParameter("score");
        String level = request.getParameter("level");
        Part file = request.getPart("file");

        Integer userId = Integer.valueOf(request.getSession().getAttribute("user_id").toString());

        Document document = null;
        if (file != null && file.getSize() > 0) {
            String originalName = file.getSubmittedFileName();
            String extension = originalName.substring(originalName.lastIndexOf("."));
            String generatedName = UUID.randomUUID() + extension;
            Document.builder()
                    .generatedFileName(generatedName)
                    .originalFileName(originalName)
                    .filePath(rootPath + "/" + generatedName)
                    .createdBy(userId) // TODO: 2/16/2023 admin id ni qo'shish kerak  qushdim
                    .build();
            file.write(generatedName);
        }

        grammar.setTitle(Objects.requireNonNullElse(title, grammar.getTitle()));
        grammar.setScore(Objects.requireNonNullElse(Integer.valueOf(score), grammar.getScore()));
        grammar.setLevel(Objects.requireNonNullElse(Levels.valueOf(level), grammar.getLevel()));
        grammar.setDocument(Objects.requireNonNullElse(document, grammar.getDocument()));
        grammar.setCreatedBy(Objects.requireNonNullElse(userId, grammar.getCreatedBy())); // TODO: 2/16/2023 admin id ni qo'shish kerak  qushdim

        teacherService.updateGrammar(grammar);

        response.sendRedirect("/teacher/grammar/list");
    }
}
