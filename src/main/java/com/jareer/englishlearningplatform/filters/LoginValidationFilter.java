package com.jareer.englishlearningplatform.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebFilter(filterName = "LoginValidationFilter", urlPatterns = {"/login"})
public class LoginValidationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String username = req.getParameter("in_username");
        String password = req.getParameter("in_password");

        if (req.getMethod().equalsIgnoreCase("post")) {
            Map<String, String> errors = new HashMap<>();

            if (username == null || username.isBlank()) {
                errors.put("username_error", "Username can not be null");
            }

            if (password == null || password.isBlank()) {
                errors.put("password_error", "Password can not be null");
            }

            if (errors.isEmpty()) {
                chain.doFilter(request, response);
            } else {
//                UserValidator.setErrorAttributes(req, errors);
                errors.forEach(req::setAttribute);
                req.getRequestDispatcher("views/authorization/login.jsp").forward(req, res);
            }
        } else {
            chain.doFilter(request, response);
        }

    }
}
