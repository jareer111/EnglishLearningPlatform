package com.jareer.englishlearningplatform.servlets.user.vocabulary;

import com.jareer.englishlearningplatform.dao.UserStoryDAO;
import com.jareer.englishlearningplatform.domains.Story;
import com.jareer.englishlearningplatform.domains.VocabHelper;
import com.jareer.englishlearningplatform.service.TeacherService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "VocabularyServlet", value = "/vocabulary")
public class VocabularyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        TeacherService teacherService = TeacherService.getInstance();
        UserStoryDAO userStoryDAO = new UserStoryDAO();
        try {
            Integer userId = (Integer) session.getAttribute("user_id");

            List<Integer> storyIds = userStoryDAO.getStoriesByUserId(userId);

            List<Story> storiesById = teacherService.getStoriesByIds(storyIds);

            List<VocabHelper> helperList = new ArrayList<>();
            storiesById.forEach(story -> {
                helperList.add(VocabHelper.builder()
                        .storyTitle(story.getTitle())
                        .storyId(story.getId())
                        .wordsCount(teacherService.getVocabularyCountWithOption(userId, story.getId()))
                        .build());
            });

            request.setAttribute("helperList", helperList);
            request.setAttribute("userId", userId);
            request.getRequestDispatcher("/views/user/vocabulary.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String storyId = request.getParameter("story_id");
        System.out.println(storyId);
        request.setAttribute("storyId" , storyId);
        request.getRequestDispatcher("/views/user/vocabulary_test.jsp").forward(request, response);

    }

}
