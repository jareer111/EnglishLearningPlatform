package com.jareer.englishlearningplatform.servlets.teacher.grammar;

import com.jareer.englishlearningplatform.domains.Grammar;
import com.jareer.englishlearningplatform.service.TeacherService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "DeleteStoryServlet", value = "/teacher/grammar/delete/*")
public class DeleteGrammarServlet extends HttpServlet {
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
        String grammarId = request.getPathInfo().substring(1);
        try {
            Grammar grammar = TeacherService.getInstance().getGrammarById(Integer.parseInt(grammarId));
            if (Objects.isNull(grammar)){
                throw new Exception("Grammar not found");
            }
            grammar.setDeleted(true);

            TeacherService.getInstance().updateAsDelete(grammar);
            response.sendRedirect("/teacher/grammar/list");
        }catch (Exception e){
            response.sendRedirect("/teacher/grammar/list");
        }
    }
}
