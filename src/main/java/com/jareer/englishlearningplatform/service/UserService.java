package com.jareer.englishlearningplatform.service;

import com.jareer.englishlearningplatform.dao.GrammarDAO;
import com.jareer.englishlearningplatform.dao.StoryDAO;
import com.jareer.englishlearningplatform.dao.UserDAO;
import com.jareer.englishlearningplatform.domains.*;
import com.jareer.englishlearningplatform.utils.Encrypt;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {
    public static final ThreadLocal<UserService> instance = ThreadLocal.withInitial(UserService::new);
    public static final int minutes = 60;
    public static final int hours = 3600;
    public static final int oneDay = 86400;

    public static UserService getInstance() {
        return instance.get();
    }

    public Cookie createCookie(String value) {
        Cookie cookie = new Cookie("remember_me", value);
        cookie.setPath("/");
        cookie.setMaxAge(oneDay);
        return cookie;
    }

    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Users newUser = Users.builder()
                .email(email)
                .username(username)
                .password(Encrypt.hashPassword(password))
                .build();

        new UserDAO().save(newUser);
        response.sendRedirect("/login");
    }

    public boolean isCorrectPassword(String password, String hashedPassword) {
        return Encrypt.checkPassword(password, hashedPassword);
    }

    public boolean isCorrectPass(String userId, String password) {
        Users user = null;
        try {
            user = new UserDAO().findById(Long.parseLong(userId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isCorrectPassword(password, user.getPassword());
    }

    public void changePassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = request.getParameter("user_id");
        String newPassword = request.getParameter("new_password");

        UserDAO instance = new UserDAO();
        Users user = instance.findById(Long.parseLong(userId));
        user.setPassword(Encrypt.hashPassword(newPassword));
        instance.update(user);
        response.sendRedirect("/logout");
    }
    public List<Grammar> getGrammarListByUserLevel(String userId) {
        List<Grammar> grammars = new ArrayList<>();
        try{
            long id = Long.parseLong(userId);
            GrammarDAO grammarDAO = new GrammarDAO();
            grammars = grammarDAO.getGrammarListByUserLevel(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return grammars;
    }
    public List<Story> getStoryListByUserLevel(String userId) {

//        int id;
//        try {
//            id = Integer.parseInt(userId);
//        }catch (Exception e){
//            return new ArrayList<>();
//        }
//        StoryDAO storyDAO = new StoryDAO();
//        return storyDAO.getStoryListByUserLevel(id);

        List<Story> stories = new ArrayList<>();
        try{
            long id = Long.parseLong(userId);
            StoryDAO storyDAO = new StoryDAO();
          stories = storyDAO.getStoryListByUserLevel(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return stories;
    }

    public List<VocabHelperTest> getQuiz(List<Vocabulary> vocabulariesByStoryId, int storyId, List<VocabHelperTest> quizHelperList) {

        for (int i = 0; i < vocabulariesByStoryId.size(); i++) {
            List<String> vocabularyList = new ArrayList<>();
            for (Vocabulary vocab : vocabulariesByStoryId) {
                vocabularyList.add(vocab.getMeaning());
            }
            //  todo javohir yozdi
            vocabularyList.remove(vocabulariesByStoryId.get(i).getMeaning());
            Collections.shuffle(vocabularyList);
            vocabularyList = vocabularyList.subList(0, 3);
            vocabularyList.add(vocabulariesByStoryId.get(i).getMeaning());
            Collections.shuffle(vocabularyList);
            VocabHelperTest vocabHelperTest = VocabHelperTest.builder().
                    vocabulary(vocabulariesByStoryId.get(i).getWord()).
                    story_id(String.valueOf(storyId)).
                    a(vocabularyList.get(0)).b(vocabularyList.get(1)).c(vocabularyList.get(2)).d(vocabularyList.get(3)).
                    correct(getCorrectValue(vocabularyList, vocabulariesByStoryId.get(i).getMeaning())).build();
            quizHelperList.add(vocabHelperTest);
        }
        return quizHelperList;
    }

    private String getCorrectValue(List<String> variants, String correct) {
        var a = variants.get(0);
        var b = variants.get(1);
        var c = variants.get(2);
        var d = variants.get(3);
        if (correct.equals(a)) {
            return "a";
        } else if (correct.equals(b)) {
            return "b";
        } else if (correct.equals(c)) {
            return "c";
        } else if (correct.equals(d)) {
            return "d";
        }
        return "";
    }

    public Users findById(Long id){
        UserDAO userDAO = new UserDAO();
        Users user = null;
        try{
            user = userDAO.findById(id);
        }catch ( Exception e ){
            e.printStackTrace();
        }
        return user;
    }



    public Users findByUsername(String username){
        UserDAO userDAO = new UserDAO();
        Users user = null;
        try{
            user = userDAO.findByUsername(username);
        }catch ( Exception e ){
            e.printStackTrace();
        }
        return user;
    }



    public boolean update( Users user ) {
        UserDAO userDAO = new UserDAO();
        return userDAO.update(user);
    }
}
