package com.jareer.englishlearningplatform.servlets.user;

import com.jareer.englishlearningplatform.domains.Users;
import com.jareer.englishlearningplatform.service.UserService;
import com.jareer.englishlearningplatform.utils.validator.UserValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "ProfileServlet", value = "/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getSession().getAttribute("user_id").toString();
        Users user = UserService.getInstance().findById(Long.valueOf(userId));
        request.setAttribute("user", user);
        request.getSession().setAttribute("user", user);


        request.getRequestDispatcher("/views/user/account-setting/profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = UserService.getInstance();
        String username = request.getParameter("username");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String userId = request.getParameter("user_id");

        Users user = userService.findById(Long.valueOf(userId));
        Map<String, String> errors = new HashMap<>();

        if (!username.trim().equals(user.getUsername())) {
            if (UserValidator.isDuplicateUsername(username)) {
                errors.put("username_error", "Username already taken!");
            } else {
                user.setUsername(username);
            }
        }

        if (!firstname.trim().equals(user.getFirstName()) && !firstname.trim().equals("Firstname")) {
            user.setFirstName(firstname);
        }

        if (!lastname.trim().equals(user.getLastName()) && !lastname.trim().equals("Lastname")) {
            user.setLastName(lastname);
        }

        if (!email.trim().equals(user.getEmail())) {
            if (UserValidator.isDuplicateEmail(email)) {
                errors.put("email_error", "Email already taken");
            } else {
                user.setEmail(email);
            }
        }
        if (errors.isEmpty()) {
            userService.update(user);
            response.sendRedirect("/profile");
        } else{
            UserValidator.setErrorAttributes(request, errors);
            request.getRequestDispatcher("/views/user/account-setting/profile.jsp").forward(request, response);
        }
    }
}
