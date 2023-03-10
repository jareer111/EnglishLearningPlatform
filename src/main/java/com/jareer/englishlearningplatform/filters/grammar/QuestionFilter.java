package com.jareer.englishlearningplatform.filters.grammar;

import com.jareer.englishlearningplatform.enums.Levels;
import com.jareer.englishlearningplatform.service.TeacherService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.java.Log;

import java.io.IOException;
import java.util.List;

@Log
@WebFilter(filterName = "StoryFilter", urlPatterns = {"/teacher/grammar/add-question/*"})
@MultipartConfig(location = "/home/javohir/apps/elearn/upload/")
public class QuestionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if (req.getMethod().equalsIgnoreCase("post")) {
            try {
                TeacherService teacherService = TeacherService.getInstance();
                teacherService.validateQuestion(req);
                chain.doFilter(req, res);
            } catch (Exception e) {
                List<Levels> levels = List.of(Levels.values());
                request.setAttribute("levels", levels);
                res.sendRedirect("/teacher/grammar/add-question/" + req.getPathInfo().substring(1));
            }
        } else {
            List<Levels> levels = List.of(Levels.values());
            request.setAttribute("levels", levels);
            chain.doFilter(req, res);
        }
    }
}
