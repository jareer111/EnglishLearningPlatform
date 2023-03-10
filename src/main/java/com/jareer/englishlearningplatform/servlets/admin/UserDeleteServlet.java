package com.jareer.englishlearningplatform.servlets.admin;

import com.jareer.englishlearningplatform.service.AdminService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "UserDeleteServlet", urlPatterns = "/user/delete/*")
public class UserDeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer userId = Integer.valueOf(request.getParameter("id"));
        AdminService.getInstance().changeDeleted(userId, true);
        response.sendRedirect("/admin-page");
    }
}
