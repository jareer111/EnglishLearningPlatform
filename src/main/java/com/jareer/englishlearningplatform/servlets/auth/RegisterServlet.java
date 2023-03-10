package com.jareer.englishlearningplatform.servlets.auth;

import com.jareer.englishlearningplatform.service.UserService;
import com.jareer.englishlearningplatform.utils.validator.UserValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/authorization/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, String> errors = UserValidator.validate(request);

        if (errors.isEmpty()) {
            UserService.getInstance().register(request, response);
        } else {
            UserValidator.setErrorAttributes(request, errors);
            request.getRequestDispatcher("views/authorization/register.jsp").forward(request, response);
        }

    }
}
