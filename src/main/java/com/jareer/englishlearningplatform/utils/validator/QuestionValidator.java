package com.jareer.englishlearningplatform.utils.validator;

import jakarta.servlet.http.HttpServletRequest;

public class QuestionValidator {
    public void validate(HttpServletRequest request) throws Exception {
        String question = request.getParameter("question");
        String option1 = request.getParameter("option1");
        String option2 = request.getParameter("option2");
        String option3 = request.getParameter("option3");
        String option4 = request.getParameter("option4");
        String correctAnswer = request.getParameter("correctAnswer");

        if (question == null || question.trim().isEmpty()) {
            throw new Exception("Title is empty");
        }

        if (option1 == null || option1.trim().isEmpty()) {
            throw new Exception("Option 1 is empty");
        }
        if (option2 == null || option2.trim().isEmpty()) {
            throw new Exception("Option 2 is empty");
        }
        if (option3 == null || option3.trim().isEmpty()) {
            throw new Exception("Option 3 is empty");
        }
        if (option4 == null || option4.trim().isEmpty()) {
            throw new Exception("Option 4 is empty");
        }
        if (correctAnswer == null || correctAnswer.trim().isEmpty()) {
            throw new Exception("Correct answer is empty");
        }
        if (!correctAnswer.endsWith("1") && !correctAnswer.endsWith("2") && !correctAnswer.endsWith("3") && !correctAnswer.endsWith("4")) {
            throw new Exception("Correct answer is invalid");
        }
    }
}
