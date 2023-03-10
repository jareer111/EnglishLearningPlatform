package com.jareer.englishlearningplatform.servlets.teacher.grammar;

import com.jareer.englishlearningplatform.domains.Grammar;
import com.jareer.englishlearningplatform.service.TeacherService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "GrammarsServlet", value = "/teacher/grammar/list")
public class GrammarsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TeacherService teachService = TeacherService.getInstance();
        List<Grammar> allGrammars = teachService.getAllGrammars();
        request.setAttribute("grammars", allGrammars);

        HttpSession session = request.getSession();
        session.setAttribute("grammars", allGrammars);

        request.getRequestDispatcher("/views/teacher/grammar/grammars.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
