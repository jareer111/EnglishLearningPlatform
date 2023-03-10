package com.jareer.englishlearningplatform.servlets.teacher.story;

import com.jareer.englishlearningplatform.domains.Story;
import com.jareer.englishlearningplatform.service.TeacherService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;
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
