package com.jareer.englishlearningplatform.servlets.admin;

import com.jareer.englishlearningplatform.domains.Users;
import com.jareer.englishlearningplatform.service.AdminService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminPageServlet", value = "/admin-page")
public class AdminPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Users> users = AdminService.getInstance().usersList(1, Integer.MAX_VALUE);
        request.setAttribute("users", users);

        request.getRequestDispatcher("/views/adminview/jsp/adminpage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
