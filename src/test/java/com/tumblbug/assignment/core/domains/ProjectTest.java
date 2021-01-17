package com.tumblbug.assignment.core.domains;

import com.tumblbug.assignment.core.domains.exceptions.MaxAmountOverException;
import com.tumblbug.assignment.core.domains.exceptions.MaxSupportCountOverException;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.UUID;

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

    @Test
    public void supportHappycaseTest() {

        Project project = Project.builder()
                .sponsoredAmount(1000L)
                .sponsoredCount(10)
                .targetAmount(10000L)
                .build();

        project.support(100L);
        assertTrue(project.getSponsoredAmount() == 1100L);
        assertTrue(project.getSponsoredCount() == 11);
    }

    @Test(expected = MaxAmountOverException.class)
    public void support_over_max_amount_test() {

        Project project = Project.builder()
                .sponsoredAmount(99999999L)
                .sponsoredCount(10)
                .build();

        project.support(2L);
    }

    @Test(expected = MaxSupportCountOverException.class)
    public void support_over_max_count_test() {

        Project project = Project.builder()
                .sponsoredAmount(0L)
                .sponsoredCount(100000)
                .build();

        project.support(100L);

    }

}