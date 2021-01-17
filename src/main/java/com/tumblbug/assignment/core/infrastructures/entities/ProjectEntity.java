package com.tumblbug.assignment.core.infrastructures.entities;

import com.tumblbug.assignment.core.domains.Project;
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
@Table(indexes = {
        @Index(columnList = "beginDate"),
        @Index(columnList = "dueDate"),
        @Index(columnList = "targetAmount"),
        @Index(columnList = "sponsoredAmount")
})
public class ProjectEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    UUID id;

    @Column(length = 50)
    String title;

    // 255 is default
//    @Column(length = 255)
    String description;

    @Builder.Default
    boolean published = true;

    @Enumerated(EnumType.STRING)
    Project.Status status;

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


    @PrePersist
    void postPersist() {
        this.createdAt = this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    void postUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
