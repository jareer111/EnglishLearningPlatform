package com.jareer.englishlearningplatform.servlets.teacher.story;

import com.jareer.englishlearningplatform.service.TeacherService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DeleteWordServlet", value = "/teacher/story/word/delete/*")
public class DeleteWordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String wordId = request.getPathInfo().substring(1);
        try {
            int id = Integer.parseInt(wordId);
            TeacherService teachService = TeacherService.getInstance();
            teachService.deleteWord(id);
            response.sendRedirect("/teacher/story/list");
        } catch (NumberFormatException e) {
            response.sendError(404, "Word with id '%s' not found".formatted(wordId));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/teacher/story/list");
    }
}
