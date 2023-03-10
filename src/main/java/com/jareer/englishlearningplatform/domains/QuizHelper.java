package com.jareer.englishlearningplatform.domains;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuizHelper {
    private String question;
    private Integer questionId;
    private String a;
    private String b;
    private String c;
    private String d;
    private String correct;
}
