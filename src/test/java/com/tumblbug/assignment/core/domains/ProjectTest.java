package com.tumblbug.assignment.core.domains;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class ProjectTest {

    @Test
    public void hasProjectClosedTest() {
        Project project = Project.builder()
                .dueDate(LocalDateTime.of(2021, 12, 31, 23, 59, 59))
                .build();

        assertTrue(project.hasProjectClosed(LocalDateTime.of(2022, 10, 1, 0, 0, 0)));
        assertTrue(project.hasProjectClosed(LocalDateTime.of(2021, 12, 31, 23, 59, 59)));
        assertFalse(project.hasProjectClosed(LocalDateTime.of(2021, 12, 31, 23, 59, 58)));
    }

    @Test
    public void hasProjectBegunTest() {
        Project project = Project.builder()
                .beginDate(LocalDateTime.of(2021, 1, 1, 0, 0, 0))
                .build();

        assertTrue(project.hasProjectBegun(LocalDateTime.of(2021, 1, 1, 0, 0, 0)));
        assertFalse(project.hasProjectBegun(LocalDateTime.of(2020, 12, 31, 23, 59, 59)));
    }

    @Test
    public void hasAchieveSponsorshipTest() {
        Project case1 = Project.builder().sponsoredAmount(1000L).targetAmount(10000L).build();
        assertFalse(case1.hasAchieveSponsorship());

        Project case2 = Project.builder().sponsoredAmount(10000L).targetAmount(1000L).build();
        assertTrue(case2.hasAchieveSponsorship());

        Project case3 = Project.builder().sponsoredAmount(10000L).targetAmount(10000L).build();
        assertTrue(case3.hasAchieveSponsorship());
    }

}