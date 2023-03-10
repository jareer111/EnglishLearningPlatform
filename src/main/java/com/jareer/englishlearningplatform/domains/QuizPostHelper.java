package com.jareer.englishlearningplatform.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizPostHelper {
    private Integer userId;
    private Integer questionId;
    private Integer score;
    private boolean isCorrect;
    private boolean isLastQuestion;

}
