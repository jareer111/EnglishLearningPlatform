package com.jareer.englishlearningplatform.servlets.user;

import com.google.gson.Gson;
import com.jareer.englishlearningplatform.domains.Story;
import com.jareer.englishlearningplatform.domains.VocabHelperTest;
import com.jareer.englishlearningplatform.domains.Vocabulary;
import com.jareer.englishlearningplatform.service.TeacherService;
import com.jareer.englishlearningplatform.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet( name = "VocabularyGetServlet", value = "/vocabulary/get/test/*" )
public class VocabularyGetServlet extends HttpServlet {
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        TeacherService teacherService = TeacherService.getInstance();

        String pathInfo = request.getPathInfo();

        try {
            int storyId = Integer.parseInt(pathInfo.substring(1));
            System.out.println(storyId);
            Story story = teacherService.getStoryById(storyId);
            List<Vocabulary> vocabulariesByStoryId = teacherService.getVocabulariesByStoryId(story);
            List<VocabHelperTest> quizHelperList = new ArrayList<>();
            List<VocabHelperTest> quiz = UserService.getInstance().getQuiz(vocabulariesByStoryId, storyId, quizHelperList);
            System.out.println("quiz=" + quiz);
            Gson gson = new Gson();
            String jsonData = gson.toJson(quizHelperList);
            response.setContentType("application/json");
            response.getWriter().println(jsonData);
        } catch ( NumberFormatException e ) {
            System.out.println("Number format exception!");
        }
    }


//    private String getCorrectValue( List<Variants> variants ) {
//        var a = variants.get(0).getVariant();
//        var b = ( variants.get(1).getVariant() );
//        var c = ( variants.get(2).getVariant() );
//        var d = ( variants.get(3).getVariant() );
//        var correct = variants.stream().filter(Variants::isCorrect).findFirst().get().getVariant();
//        if ( correct.equals(a) ) {
//            return "a";
//        } else if ( correct.equals(b) ) {
//            return "b";
//        } else if ( correct.equals(c) ) {
//            return "c";
//        } else if ( correct.equals(d) ) {
//            return "d";
//        }
//        return "";
//    }

    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

    }
}
