package com.jareer.englishlearningplatform.servlets.teacher.story;

import com.jareer.englishlearningplatform.domains.Story;
import com.jareer.englishlearningplatform.service.TeacherService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "DeleteStoryServlet", value = "/teacher/story/delete/*")
public class DeleteStoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String storyId = request.getPathInfo().substring(1);
        try {
            request.getRequestDispatcher("/views/teacher/story/delete.jsp").forward(request, response);
        }catch (Exception e){
            response.sendRedirect("/teacher/story/list");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String storyId = request.getPathInfo().substring(1);
        try {
            Story story = TeacherService.getInstance().getStoryById(Integer.parseInt(storyId));
            if (Objects.isNull(story)){
                throw new Exception("Story not found");
            }
            story.setDeleted(true);

            TeacherService.getInstance().updateAsDelete(story);
            response.sendRedirect("/teacher/story/list");
        }catch (Exception e){
            response.sendRedirect("/teacher/story/list");
        }
    }
}
