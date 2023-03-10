package com.jareer.englishlearningplatform.domains.leveltest;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@Getter
@Setter

@RequiredArgsConstructor
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String option;
    @ManyToOne(cascade = CascadeType.ALL)
    private LevelQuestion levelQuestion;

    @Column(nullable = false)
    private Boolean isCorrect;
}
