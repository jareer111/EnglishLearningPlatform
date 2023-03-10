package com.jareer.englishlearningplatform.domains.leveltest;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@Getter
@Setter

@RequiredArgsConstructor
public class LevelQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String question;

    @Column(nullable = false)
    private Short points;
}
