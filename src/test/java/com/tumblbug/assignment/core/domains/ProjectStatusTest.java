package com.tumblbug.assignment.core.domains;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class ProjectStatusTest {

    @Test
    public void getStatusShouldReturn_PREPARING_Test() {
        Project project = Project.builder()
                .beginDate(LocalDateTime.of(2021, 1, 1, 0, 0, 0))
                .build();

        Project.Status actual = project.getStatus(LocalDateTime.of(2020, 12, 31, 23, 59, 59));

        assertEquals(Project.Status.PREPAIRING, actual);
    }

    @Test
    public void getStatusShouldReturn_IN_PROGRESS_Test() {
        Project project = Project.builder()
                .beginDate(LocalDateTime.of(2021, 1, 1, 0, 0, 0))
                .dueDate(LocalDateTime.of(2022, 1, 1, 0, 0, 0))
                .build();

        Project.Status actual = project.getStatus(LocalDateTime.of(2021, 1, 1, 0, 0, 1));

        assertEquals(Project.Status.IN_PROGRESS, actual);
    }

    @Test
    public void getStatusShouldReturn_SUCCEED_Test() {
        Project project = Project.builder()
                .beginDate(LocalDateTime.of(2020, 1, 1, 0,0,0))
                .dueDate(LocalDateTime.of(2021, 1, 1, 0, 0, 0))
                .sponsoredAmount(1000L)
                .targetAmount(1000L)
                .build();

        Project.Status actual = project.getStatus(LocalDateTime.of(2021, 1, 1, 0, 0, 1));
        assertEquals(Project.Status.SUCCEED, actual);
    }

    @Test
    public void getStatusShouldReturn_FAILED_Test() {
        Project project = Project.builder()
                .beginDate(LocalDateTime.of(2020, 1, 1, 0,0,0))
                .dueDate(LocalDateTime.of(2021, 1, 1, 0, 0, 0))
                .sponsoredAmount(1000L)
                .targetAmount(10000L)
                .build();

        Project.Status actual = project.getStatus(LocalDateTime.of(2021, 1, 1, 0, 0, 1));

        assertEquals(Project.Status.FAILED, actual);
    }

}