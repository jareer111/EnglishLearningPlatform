package com.jareer.englishlearningplatform.utils.validator;

import com.jareer.englishlearningplatform.dao.UserDAO;
import com.jareer.englishlearningplatform.domains.Users;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserValidator {

    public static final String USERNAME_REGEX = "^[a-zA-Z0-9_-]{3,25}$";
    public static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=.!_])(?=\\S+$).{7,20}$";
    public static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    public static boolean validateUsername(String username) {
        return username.matches(USERNAME_REGEX);
    }

    public static boolean validatePassword(String password) {
        return password.matches(PASSWORD_REGEX);
    }

    public static boolean validateEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }

    public static Map<String, String> validate(HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        if (!validateUsername(username)) {
            errors.put("username", "Username is not valid");
        }
        if (password.contains(" ")) {
            errors.put("password", "White space is not allowed!");
        } else if (!validatePassword(password)) {
            errors.put("password", "Choose a stronger password");
        }
        if (!validateEmail(email)) {
            errors.put("email", "Email is not valid");
        }
        return errors;
    }

    public static HttpServletRequest setErrorAttributes(HttpServletRequest request, Map<String, String> errors) {
        for (Map.Entry<String, String> entry : errors.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }
        return request;
    }

    // check for duplicate username
    public static boolean isDuplicateUsername(String username) {
        List<Users> users = new UserDAO().findAll();
        for (Users user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    // check for duplicate email
    public static boolean isDuplicateEmail(String email) {
        List<Users> users = new UserDAO().findAll();
        for (Users user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }


}
