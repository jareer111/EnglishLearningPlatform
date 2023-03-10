package com.jareer.englishlearningplatform.utils.validator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public class GrammarValidator {

        public void validate(HttpServletRequest request) throws Exception {
            String title = request.getParameter("title");
            String score = request.getParameter("score");
            String level = request.getParameter("level");
            Part file = request.getPart("file");

            if (title == null || title.trim().isEmpty()) {
                throw new Exception("Title is empty");
            }

            if (score == null || score.trim().isEmpty() || Integer.parseInt(score) < 0 || Integer.parseInt(score) > 50) {
                throw new Exception("Content is empty");
            }

            if (level == null || level.trim().isEmpty()) {
                throw new Exception("Level is empty");
            }

            if (file == null || file.getSize() == 0) {
                throw new Exception("Image is empty");
            }
        }
}
