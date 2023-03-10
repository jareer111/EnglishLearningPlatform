package com.jareer.englishlearningplatform.servlets.teacher.story;

import com.jareer.englishlearningplatform.domains.Story;
import com.jareer.englishlearningplatform.service.TeacherService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "StoriesServlet", value = "/teacher/story/list")
public class StoriesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TeacherService teachService = TeacherService.getInstance();
        List<Story> allStories = teachService.getAllStories();
        request.setAttribute("stories", allStories);

        HttpSession session = request.getSession();
        session.setAttribute("stories", allStories);

        request.getRequestDispatcher("/views/teacher/story/stories.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
