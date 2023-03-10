  package com.jareer.englishlearningplatform.domains.newStructure;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Embeddable
public abstract class Auditable {

    private boolean deleted;
    @CreationTimestamp
    @Column(nullable = false,
            columnDefinition = "timestamp default now()",
            updatable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false, updatable = false)
    private Integer createdBy;
    private LocalDateTime updatedAt;
    private Integer updatedBy;
}