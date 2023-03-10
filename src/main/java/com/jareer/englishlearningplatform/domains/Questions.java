package com.jareer.englishlearningplatform.domains;

import com.jareer.englishlearningplatform.domains.newStructure.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Builder
@AllArgsConstructor
@Getter
@Setter

@RequiredArgsConstructor


public class Questions implements BaseEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;
    @Column( nullable = false )
    private String title;
    @JoinColumn( nullable = false )
    @ManyToOne( cascade = CascadeType.MERGE, fetch = FetchType.LAZY )
    private Grammar grammar;



    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || Hibernate.getClass(this) != Hibernate.getClass(o) ) return false;
        Questions questions = (Questions) o;
        return id != null && Objects.equals(id, questions.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}' + "\n";
    }


}
