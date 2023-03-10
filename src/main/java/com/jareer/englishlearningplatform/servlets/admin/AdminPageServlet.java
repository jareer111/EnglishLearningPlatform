package com.jareer.englishlearningplatform.servlets.admin;

import com.jareer.englishlearningplatform.domains.Users;
import com.jareer.englishlearningplatform.service.AdminService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminPageServlet", value = "/admin-page")
public class AdminPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = Integer.valueOf(request.getSession().getAttribute("user_id").toString());
        List<Users> users = AdminService.getInstance().usersList(userId,1, Integer.MAX_VALUE);
        request.setAttribute("users", users);

        request.getRequestDispatcher("/views/adminview/jsp/adminpage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
