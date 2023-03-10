package com.jareer.englishlearningplatform.domains;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VocabHelper {
    private Integer storyId;
    private String storyTitle;
    private Integer wordsCount;
}
