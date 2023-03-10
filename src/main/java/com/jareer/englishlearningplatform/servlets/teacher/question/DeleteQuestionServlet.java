package com.jareer.englishlearningplatform.servlets.teacher.question;

import com.jareer.englishlearningplatform.service.TeacherService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DeleteQuestionServlet", value = "/teacher/grammar/delete-question/*")
public class DeleteQuestionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/teacher/grammar/delete-question.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String questionId = request.getPathInfo().substring(1);
        try {
            int id = Integer.parseInt(questionId);
            TeacherService teacherService = TeacherService.getInstance();
            teacherService.deleteQuestion(id);
            response.sendRedirect("/teacher/grammar/list");
        } catch (NumberFormatException e) {
            response.sendRedirect("/teacher/grammar/list");
        }
    }
}
