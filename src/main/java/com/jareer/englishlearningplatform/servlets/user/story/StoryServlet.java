package com.jareer.englishlearningplatform.servlets.user.story;

import com.jareer.englishlearningplatform.dao.DocumentDAO;
import com.jareer.englishlearningplatform.dao.UserDAO;
import com.jareer.englishlearningplatform.dao.UserStoryDAO;
import com.jareer.englishlearningplatform.dao.VocabularyDAO;
import com.jareer.englishlearningplatform.domains.*;
import com.jareer.englishlearningplatform.enums.Levels;
import com.jareer.englishlearningplatform.service.TeacherService;
import com.jareer.englishlearningplatform.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "ReadStoryServlet", value = "/reading")
public class StoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        TeacherService teacherService = TeacherService.getInstance();
        UserService userService = UserService.getInstance();
        try {
            Long userId = Long.parseLong(String.valueOf(session.getAttribute("user_id")));
            Users byIdUser = userService.findById(userId);
            Levels userLevel = byIdUser.getLevel();
            Story storyWithOption = teacherService.getStoryWithOption(userLevel.name(), userId);
            if (Objects.isNull(storyWithOption)) storyWithOption = teacherService.getStoryById(2);
            List<Vocabulary> allByVocabulary = teacherService.getVocabulariesByStoryId(storyWithOption);
            request.setAttribute("vocabularyList", allByVocabulary);
            request.setAttribute("storyId", storyWithOption.getId());
            request.setAttribute("userId", userId);
            Document byIdDoc = teacherService.findGrammarById(storyWithOption.getDocument().getId());

            URL domain = new URL("http://localhost:8080/");
            URL url = new URL(domain + byIdDoc.getFilePath());
            System.out.println(url);
            request.setAttribute("file", url);

            request.getRequestDispatcher("/views/user/stories.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            e.getMessage();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TeacherService teacherService = TeacherService.getInstance();
        try {

            int storyId = Integer.parseInt(request.getParameter("storyId"));
            System.out.println(storyId);
            int userId = Integer.parseInt(request.getParameter("userId"));
            System.out.println(userId);
            String feedback = request.getParameter("feedback");
            String e_word = request.getParameter("eword");
            String u_word = request.getParameter("uword");
            Story story = teacherService.findStoryById(storyId);
            if (Objects.nonNull(e_word) && Objects.nonNull(u_word) && !e_word.equals("") && !u_word.equals("")) {
                VocabularyDAO vocabularyDAO = new VocabularyDAO();
                boolean hasWord = vocabularyDAO.hasWord(e_word, u_word);
                if (!hasWord) {
                    vocabularyDAO.save(Vocabulary.builder()
                            .story(story)
                            .word(e_word)
                            .meaning(u_word)
                            .createdBy(userId)
                            .build());
                }
            }
            System.out.println(feedback);
            Story byIdStory = story;
            UserStoryDAO userStoryDAO = new UserStoryDAO();
            userStoryDAO.save(User_Story.builder()
                    .story_id(storyId)
                    .user_id(userId)
                    .is_saved(true)
                    .build());
            UserDAO userDAO = new UserDAO();
            Users byIdUser = userDAO.findById(Long.valueOf(String.valueOf(userId)));
            Levels userLevel = byIdUser.getLevel();
            VocabularyDAO vocabularyDAO = new VocabularyDAO();
            Story storyWithOption = teacherService.getStoryWithOption(userLevel.name(), Long.valueOf(String.valueOf(userId)));
            if (Objects.isNull(storyWithOption)) storyWithOption = teacherService.getStoryById(2);
            List<Vocabulary> allByVocabulary = vocabularyDAO.getVocabulariesByStoryId(storyWithOption);
            request.setAttribute("vocabularyList", allByVocabulary);
            DocumentDAO documentDAO = new DocumentDAO();
            Document byIdDoc = documentDAO.findById(storyWithOption.getDocument().getId());
            request.setAttribute("file", byIdDoc.getFilePath());
            request.setAttribute("storyId", storyId);
            request.setAttribute("userId", userId);
            request.getRequestDispatcher("/views/user/stories.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }


    }


}
