package com.jareer.englishlearningplatform.servlets.user;

import com.google.gson.Gson;
import com.jareer.englishlearningplatform.domains.Users;
import com.jareer.englishlearningplatform.enums.Levels;
import com.jareer.englishlearningplatform.service.UserService;
import com.jareer.englishlearningplatform.utils.Response;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "LevelTest", value = "/assessment")
public class LevelTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/user/assesment/LevelTest.html").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        String userId = request.getSession().getAttribute("user_id").toString();
        Long id = Long.parseLong(userId);
        UserService userService = UserService.getInstance();
        Users user = userService.findById(id);
        String requestBody = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
        Response result = gson.fromJson(requestBody, Response.class);
        int score = result.getScore();
        if (score <= 5) {
            user.setLevel(Levels.BEGINNER);
            userService.update(user);
        } else {
            if (score <= 9) {
                user.setLevel(Levels.ELEMENTARY);
            } else if (score <= 14) {
                user.setLevel(Levels.PRE_INTERMEDIATE);
            } else if (score <= 18) {
                user.setLevel(Levels.INTERMEDIATE);
            } else if (score <= 23) {
                user.setLevel(Levels.UPPER_INTERMEDIATE);
            } else if (score <= 26) {
                user.setLevel(Levels.ADVANCED);
            } else {
                user.setLevel(Levels.PROFICIENCY);
            }
            userService.update(user);
        }
        System.out.println(user);
    }

}
