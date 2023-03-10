package com.jareer.englishlearningplatform.domains;

import com.jareer.englishlearningplatform.domains.newStructure.BaseEntity;
import com.jareer.englishlearningplatform.enums.Levels;
import com.jareer.englishlearningplatform.enums.Roles;
import com.jareer.englishlearningplatform.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Builder
@AllArgsConstructor

public class Users implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private Roles role = Roles.USER;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Levels level=Levels.DEFAULT;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.ACTIVE;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false, columnDefinition = "integer default 0")
    @Builder.Default
    private Integer lastTestID = 0;
    private LocalDate birthDate;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean gender;
    @Column(nullable = false, columnDefinition = "integer default 0")
    @Builder.Default
    private Integer score = 0;
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean deleted;

   

    
    private Users() {
        
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Users users = (Users) o;
        return id != null && Objects.equals(id, users.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
