package com.jareer.englishlearningplatform.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VocabHelperTest {
    private String vocabulary;
    private String story_id;
    private String a;
    private String b;
    private String c;
    private String d;
    private String correct;
}
