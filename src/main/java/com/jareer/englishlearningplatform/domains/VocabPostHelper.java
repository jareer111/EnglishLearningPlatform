package com.jareer.englishlearningplatform.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VocabPostHelper {
    private Integer userId;
    private Integer story_id;
    private Integer score;
    private boolean isCorrect;
    private boolean isLastQuestion;

}
