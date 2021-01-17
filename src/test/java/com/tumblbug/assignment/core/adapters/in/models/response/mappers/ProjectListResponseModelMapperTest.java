package com.tumblbug.assignment.core.adapters.in.models.response.mappers;

import com.tumblbug.assignment.core.adapters.in.models.response.ProjectListResponseModel;
import com.tumblbug.assignment.core.domains.Creator;
import com.tumblbug.assignment.core.domains.Project;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.Assert.*;

public class ProjectListResponseModelMapperTest {

    ProjectListResponseModelMapper mapper = new ProjectListResponseModelMapper();

    @Test
    public void projectListResponseModelMapperTest() {

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

        ProjectListResponseModel responseModel = this.mapper.map(project);

        assertEquals(uuid.toString(), responseModel.getId());
        assertEquals("타이틀", responseModel.getTitle());
        assertEquals("홍길동", responseModel.getCreatorName());
        assertEquals(Long.valueOf(100000L), responseModel.getTargetAmount());
        assertEquals(Long.valueOf(1000L), responseModel.getSponsoredAmount());
        assertEquals(Integer.valueOf(10), responseModel.getSponsoredCount());
        assertEquals(dueDate, responseModel.getDueDate());
        assertEquals(beginDate, responseModel.getBeginDate());
        assertEquals("진행중", responseModel.getStatus());


    }
}