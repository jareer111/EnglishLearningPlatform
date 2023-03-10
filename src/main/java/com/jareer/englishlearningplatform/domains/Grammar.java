package com.jareer.englishlearningplatform.domains;

import com.jareer.englishlearningplatform.domains.newStructure.BaseEntity;
import com.jareer.englishlearningplatform.enums.Levels;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor

@AllArgsConstructor
@Builder
public class Grammar implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title", nullable = false, unique = true)
    private String title;
    @JoinColumn(nullable = false)
    @OneToOne(cascade = CascadeType.ALL)
    private Document document;

    @Column(nullable = false)
    private Integer score;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Levels level;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean deleted;
    @Column(nullable = false)
    private Integer createdBy;
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Grammar grammar = (Grammar) o;
        return id != null && Objects.equals(id, grammar.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
