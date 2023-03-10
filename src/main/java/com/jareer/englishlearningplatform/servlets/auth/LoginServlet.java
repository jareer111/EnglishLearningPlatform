package com.jareer.englishlearningplatform.servlets.auth;

import com.jareer.englishlearningplatform.domains.Users;
import com.jareer.englishlearningplatform.service.UserService;
import com.jareer.englishlearningplatform.utils.AES;
import com.jareer.englishlearningplatform.utils.Utils;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet( name = "LoginServlet", value = "/login" )
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        request.getRequestDispatcher("/views/authorization/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        String url = request.getQueryString();
        UserService userService = UserService.getInstance();
        String username = request.getParameter("in_username");
        String password = request.getParameter("in_password");

        Users user = userService.findByUsername(username);


        if ( user == null ) {
            request.setAttribute("error", "Username or password is incorrect");
            request.getRequestDispatcher("/views/authorization/login.jsp").forward(request, response);
        } else {
            if ( user.isDeleted() ) {
                request.setAttribute("error", "Your account has been blocked!");
                request.getRequestDispatcher("/views/authorization/login.jsp").forward(request, response);
            } else if ( UserService.getInstance().isCorrectPassword(password, user.getPassword()) ) {

                String userId = String.valueOf(user.getId());
                String encrypt = AES.encrypt(userId);
                Cookie cookie = UserService.getInstance().createCookie(encrypt);
                response.addCookie(cookie);
                response.sendRedirect(( url != null ) ? url.substring(5) : Utils.redirectByRole(user));
            } else {
                request.setAttribute("error", "Username or password is incorrect");
                request.getRequestDispatcher("/views/authorization/login.jsp").forward(request, response);
            }
        }
    }
}
