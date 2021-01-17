package com.tumblbug.assignment.core.domains;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;
import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Project {

    @Getter
    @AllArgsConstructor
    public enum Status {
        PREPAIRING("준비중", (now, project) -> !project.hasProjectBegun(now)),
        IN_PROGRESS("진행중", (now, project) -> project.hasProjectBegun(now) && !project.hasProjectClosed(now)),
        SUCCEED("성공", (now, project) -> project.hasProjectClosed(now) && project.hasAchieveSponsorship()),
        FAILED("실패", (now, project) -> project.hasProjectClosed(now) && !project.hasAchieveSponsorship());

        public final String description;

        public final BiFunction<LocalDateTime, Project, Boolean> fitToThis;

        private static final String EXCEPTION_MESSAGE = "cannot determine the status of project.";

        public static Status determineStatus(LocalDateTime now, Project project) {

            return Arrays.stream(values())
                    .filter(s -> s.fitToThis.apply(now, project))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException(EXCEPTION_MESSAGE));
        }
    }

    UUID id;

    String title; // 프로젝트 제목
    String description; // 프로젝트 설명

    @Builder.Default
    boolean published = true; // 공개여부

    LocalDateTime beginDate; // 프로젝트 시작일
    LocalDateTime dueDate; // 프로젝트 마감

    Long targetAmount;

    Integer sponsoredCount; // 후원수
    Long sponsoredAmount; // 후원액

    Creator creator;

    // 프로젝트 종료일이 지났는가?
    public boolean hasProjectClosed(LocalDateTime now) {
        return now.isEqual(this.dueDate) || now.isAfter(this.dueDate);
    }

    // 프로젝트 시작일이 지났는가?
    public boolean hasProjectBegun(LocalDateTime now) {
        return now.isEqual(this.beginDate) || now.isAfter(this.beginDate);
    }

    // 후원금액이 목표액을 달성하였는가?
    public boolean hasAchieveSponsorship() {
        return sponsoredAmount >= targetAmount;
    }

    public Status getStatus(LocalDateTime now) {
        return Status.determineStatus(now, this);
    }

    public Status getStatus() {
        return this.getStatus(LocalDateTime.now());
    }

    public void support(long amount) {
        this.sponsoredAmount += amount;
        this.sponsoredCount += 1;
    }
}

