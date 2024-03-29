package com.tumblbug.assignment.core.adapters.in.models.response.mappers;

import com.tumblbug.assignment.core.adapters.in.models.response.ProjectResponseModel;
import com.tumblbug.assignment.core.domains.Creator;
import com.tumblbug.assignment.core.domains.Project;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.Assert.*;

public class ProjectResponseModelMapperTest {

    ProjectResponseModelMapper mapper = new ProjectResponseModelMapper();

    @Test
    public void ProjectToProjectResponseModelMapperTest() {
        UUID uuid = UUID.randomUUID();
        LocalDateTime beginDate = LocalDateTime.of(2021, 1, 1, 00, 00, 00);
        LocalDateTime dueDate = LocalDateTime.of(2099, 10, 1, 12, 00, 00);

        Project project = Project.builder()
                .id(uuid)
                .title("타이틀")
                .description("설명")
                .targetAmount(100000L)
                .sponsoredAmount(1000L)
                .dueDate(dueDate)
                .beginDate(beginDate)
                .sponsoredCount(10)
                .published(true)
                .creator(Creator.builder()
                        .name("홍길동")
                        .phone("010-1234-5678")
                        .email("gildong@email.com")
                        .build())
                .build();

        ProjectResponseModel responseModel = this.mapper.map(project);

        assertEquals("타이틀", responseModel.getTitle());
        assertEquals("설명", responseModel.getDescription());
        assertEquals(Long.valueOf(100000L), responseModel.getTargetAmount());
        assertEquals(Long.valueOf(1000L), responseModel.getSponsoredAmount());
        assertEquals(dueDate, responseModel.getDueDate());
        assertEquals(beginDate, responseModel.getBeginDate());
        assertEquals(Integer.valueOf(10), responseModel.getSponsoredCount());
        assertEquals(true, responseModel.isPublished());
        assertEquals("홍길동", responseModel.getCreatorName());
        assertEquals("010-1234-5678", responseModel.getCreatorPhone());
        assertEquals("gildong@email.com", responseModel.getCreatorEmail());
        assertEquals("진행중", responseModel.getStatus());

    }

}