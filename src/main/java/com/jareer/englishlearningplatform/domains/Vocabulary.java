package com.jareer.englishlearningplatform.domains;

import com.jareer.englishlearningplatform.domains.newStructure.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "vocabulary")
@AllArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
public class Vocabulary implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Story story;

    @Column(nullable = false)
    private String word;
    @Column(nullable = false)
    private String meaning;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean deleted;

    @ToString.Exclude
    @Column(nullable = false)
    private Integer createdBy;


    private Integer deletedBy;
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    private LocalDateTime deletedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Vocabulary that = (Vocabulary) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
