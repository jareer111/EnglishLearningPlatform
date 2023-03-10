package com.jareer.englishlearningplatform.domains;

import com.jareer.englishlearningplatform.domains.newStructure.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class VocabTestsHistory implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Integer story_id;
    @Column(nullable = false)
    private Integer user_id;
    @Column(nullable = false)
    private String words;
    @Column(nullable = false)
    private boolean is_correct;

    @Column(nullable = false , columnDefinition = "boolean default false")
    private boolean has_been_tested;
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Builder.Default
    private LocalDateTime created_at=LocalDateTime.now();
}
