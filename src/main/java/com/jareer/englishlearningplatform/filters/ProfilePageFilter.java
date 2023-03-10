package com.jareer.englishlearningplatform.filters;

import com.jareer.englishlearningplatform.utils.validator.UserValidator;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebFilter(filterName = "ProfilePageFilter", urlPatterns = {"/profile"})
public class ProfilePageFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String username = req.getParameter("username");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");

        if (req.getMethod().equalsIgnoreCase("post")) {
            Map<String, String> errors = new HashMap<>();
            if (username == null || username.isBlank() || username.isEmpty()) {
                errors.put("username_error", "Username can not be blank");
            }

            if (firstname == null || firstname.isBlank() || firstname.isEmpty()) {
                errors.put("firstname_err", "Firstname can not be blank");
            }

            if (lastname == null || lastname.isBlank() || lastname.isEmpty()) {
                errors.put("lastname_err", "Lastname can not be blank");
            }

            if (email == null || email.isBlank() || email.isEmpty()) {
                errors.put("email_error", "Email can not be blank");
            }

            if (errors.isEmpty()) {
                chain.doFilter(request, response);
            } else {
                UserValidator.setErrorAttributes(req, errors);
                req.getRequestDispatcher("/views/user/account-setting/profile.jsp").forward(req, res);
            }
        } else {
            chain.doFilter(request, response);
        }
    }
}
