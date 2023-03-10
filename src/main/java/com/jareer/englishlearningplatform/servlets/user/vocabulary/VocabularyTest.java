package com.jareer.englishlearningplatform.servlets.user.vocabulary;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "VocabularyTest", value = "/vocabulary/test/*")
public class VocabularyTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        Integer storyId = Integer.parseInt(pathInfo.substring(1));
        req.setAttribute("storyId" , storyId);
        req.getRequestDispatcher("/views/user/vocabulary_test.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
