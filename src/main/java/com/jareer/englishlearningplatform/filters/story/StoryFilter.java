package com.jareer.englishlearningplatform.filters.story;

import com.jareer.englishlearningplatform.enums.Levels;
import com.jareer.englishlearningplatform.utils.validator.StoryAddValidator;
import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.java.Log;

import java.io.IOException;
import java.util.List;

@Log
@WebFilter(filterName = "StoryFilter", urlPatterns = {"/teacher/story/add"})
@MultipartConfig
public class StoryFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if (req.getMethod().equalsIgnoreCase("post")) {
            StoryAddValidator validator = StoryAddValidator.getInstance();
            try {
                validator.validate(req);
                chain.doFilter(req, res);
            } catch (Exception e) {
                List<Levels> levels = List.of(Levels.values());
                request.setAttribute("levels", levels);
                request.getRequestDispatcher("/views/teacher/story/story-add.jsp").forward(request, response);
            }
        } else {
            System.out.println("Post method emas");
            List<Levels> levels = List.of(Levels.values());
            request.setAttribute("levels", levels);
            chain.doFilter(req, res);
        }
    }
}
