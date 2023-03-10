package com.jareer.englishlearningplatform.domains;

import com.jareer.englishlearningplatform.domains.newStructure.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class User_Story implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Integer user_id;
    private Integer story_id;
    @Column(name = "grammar_id")
    private Integer grammar_id;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean is_saved;

}
