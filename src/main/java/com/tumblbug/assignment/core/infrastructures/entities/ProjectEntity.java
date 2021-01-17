package com.tumblbug.assignment.core.infrastructures.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Project")
public class ProjectEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    UUID id;

    @Column(length = 50)
    String title;

    String description;

    @Builder.Default
    boolean published = true;

    String status;

    LocalDateTime beginDate;
    LocalDateTime dueDate;

    Long targetAmount;
    Long sponsoredAmount;

    Integer sponsoredCount;

    @Column(length = 20)
    String creatorName;

    String creatorEmail;

    @Column(length = 13)
    String creatorPhone;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;


    @PostPersist
    void postPersist() {
        this.createdAt = this.updatedAt = LocalDateTime.now();
    }

    @PostUpdate
    void postUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
