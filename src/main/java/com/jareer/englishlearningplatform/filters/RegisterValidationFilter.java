package com.jareer.englishlearningplatform.filters;

import com.jareer.englishlearningplatform.utils.validator.UserValidator;
import jakarta.persistence.ManyToOne;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebFilter(filterName = "RegisterValidationFilter", urlPatterns = {"/register"})
public class RegisterValidationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm_password");

        if (req.getMethod().equalsIgnoreCase("post")) {
            Map<String, String> errors = new HashMap<>();
            if (username == null || username.isBlank() || username.isEmpty()) {
                errors.put("username_error", "Username can not be null");
            } else if (UserValidator.isDuplicateUsername(username)) {
                errors.put("username_error", "Username already taken!");
            }

            if (password == null || password.isBlank() || password.isEmpty()) {
                errors.put("pass_conf_err", "Password can not be null");
            } else if (confirmPassword == null || confirmPassword.isBlank() || confirmPassword.isEmpty()) {
                errors.put("pass_conf_err", "Confirm password can not be null");
            } else if (!password.equals(confirmPassword)) {
                errors.put("pass_conf_err", "Passwords don't match!");
            }

            if (email == null || email.isBlank() || email.isEmpty()) {
                errors.put("email_error", "Email can not be null");
            } else if (UserValidator.isDuplicateEmail(email)) {
                errors.put("email_error", "Email already taken");
            }

            if (errors.isEmpty()) {
                chain.doFilter(request, response);
            } else {
                UserValidator.setErrorAttributes(req, errors);
                req.getRequestDispatcher("views/authorization/register.jsp").forward(req, res);
            }
        } else {
            chain.doFilter(request, response);
        }
    }
}
