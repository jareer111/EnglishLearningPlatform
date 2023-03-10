package com.jareer.englishlearningplatform.domains;

import com.jareer.englishlearningplatform.domains.newStructure.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Builder

@AllArgsConstructor
public class Document implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String generatedFileName;
    @Column(nullable = false)
    private String originalFileName;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean deleted;
    @Column(nullable = false)
    private Integer createdBy;
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    @Column(nullable = false)
    private String filePath;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Document document = (Document) o;
        return id != null && Objects.equals(id, document.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
