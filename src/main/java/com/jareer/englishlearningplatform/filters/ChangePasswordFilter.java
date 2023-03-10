package com.jareer.englishlearningplatform.filters;

import com.jareer.englishlearningplatform.service.UserService;
import com.jareer.englishlearningplatform.utils.validator.UserValidator;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebFilter(filterName = "ChangePasswordFilter", urlPatterns = {"/change-password"})
public class ChangePasswordFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String userId = req.getSession().getAttribute("user_id").toString();
//        String userId = req.getParameter("user_id");

        String oldPassword = req.getParameter("old_password");
        String newPassword = req.getParameter("new_password");
        String confirmPassword = req.getParameter("confirm_password");

        if (req.getMethod().equalsIgnoreCase("post")) {

            Map<String, String> errors = new HashMap<>();

            if (oldPassword == null || oldPassword.isBlank() || oldPassword.isEmpty()) {
                errors.put("old_pass_error", "Old password can not be null");
            } else if (!UserService.getInstance().isCorrectPass(userId, oldPassword)) {
                errors.put("old_pass_error", "Incorrect password");
            }

            if (newPassword == null || newPassword.isBlank() || newPassword.isEmpty()) {
                errors.put("new_pass_error", "New password can not be null");
            } else if (newPassword.equals(oldPassword)) {
                errors.put("new_pass_error", "New password and old password cannot be the same!");
            }

            if (confirmPassword == null || confirmPassword.isBlank() || confirmPassword.isEmpty()) {
                errors.put("conf_pass_error", "Confirm password can not be null");
            } else if (confirmPassword.equals(oldPassword)) {
                errors.put("conf_pass_error", "New password and old password cannot be the same!");
            }

            if (newPassword != null && confirmPassword != null && !confirmPassword.equals(newPassword)) {
                errors.put("pass_match_error", "Passwords don't match!");
            }

            if (errors.isEmpty()) {
                chain.doFilter(request, response);
            } else {
                UserValidator.setErrorAttributes(req, errors);
                req.getRequestDispatcher("views/authorization/change-password.jsp").forward(req, res);
            }
        } else {
            chain.doFilter(request, response);
        }
        chain.doFilter(request, response);
    }
}
