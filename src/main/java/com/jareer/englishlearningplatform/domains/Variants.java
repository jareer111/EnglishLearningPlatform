package com.jareer.englishlearningplatform.domains;

import com.jareer.englishlearningplatform.domains.newStructure.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Builder
@Table(name = "variants")
@AllArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor
public class Variants implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String variant;
    private boolean isCorrect;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Questions questions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Variants variants = (Variants) o;
        return id != null && Objects.equals(id, variants.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Variant{" +
                "id=" + id +
                ", variant='" + variant + '\'' +
                ", isCorrect='" + isCorrect + '\'' +
                '}' + "\n";
    }
}
