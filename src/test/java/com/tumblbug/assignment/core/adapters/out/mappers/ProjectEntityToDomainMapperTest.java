package com.tumblbug.assignment.core.adapters.out.mappers;

import com.tumblbug.assignment.core.domains.Creator;
import com.tumblbug.assignment.core.domains.Project;
import com.tumblbug.assignment.core.infrastructures.entities.ProjectEntity;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.Assert.*;

public class ProjectEntityToDomainMapperTest {

    ProjectEntityToDomainMapper projectEntityToDomainMapper = new ProjectEntityToDomainMapper();

    @Test
    public void mapEntityToDomainTest() {
        UUID id = UUID.randomUUID();
        LocalDateTime beginDate = LocalDateTime.of(2020, 1, 1, 0, 0, 0);
        LocalDateTime dueDate = LocalDateTime.of(2021, 1,1, 23, 59, 59);
        ProjectEntity entity = ProjectEntity.builder()
                .id(id)
                .beginDate(beginDate)
                .dueDate(dueDate)
                .sponsoredAmount(1000L)
                .sponsoredCount(10)
                .creatorEmail("abc@naver.com")
                .creatorName("홍길동")
                .creatorPhone("010-1234-1234")
                .title("타이틀")
                .description("설명")
                .targetAmount(10000L)
                .build();

        Project domain = this.projectEntityToDomainMapper.map(entity);

        assertEquals(id, domain.getId());
        assertEquals(beginDate, domain.getBeginDate());
        assertEquals(dueDate, domain.getDueDate());
        assertTrue(1000L == domain.getSponsoredAmount());
        assertTrue(10 == domain.getSponsoredCount());
        assertEquals("abc@naver.com", domain.getCreator().getEmail());
        assertEquals("홍길동", domain.getCreator().getName());
        assertEquals("010-1234-1234", domain.getCreator().getPhone());
        assertEquals("타이틀", domain.getTitle());
        assertEquals("설명", domain.getDescription());
    }

    @Test
    public void mapDomainToEntityTest() {
        UUID id = UUID.randomUUID();
        LocalDateTime beginDate = LocalDateTime.of(2020, 1, 1, 0, 0, 0);
        LocalDateTime dueDate = LocalDateTime.of(2099, 1,1, 23, 59, 59);
        Project project = Project.builder()
                .id(id)
                .beginDate(beginDate)
                .dueDate(dueDate)
                .sponsoredAmount(1000L)
                .sponsoredCount(10)
                .creator(Creator.builder()
                        .email("abc@naver.com")
                        .name("홍길동")
                        .phone("010-1234-1234")
                        .build())
                .title("타이틀")
                .description("설명")
                .targetAmount(10000L)
                .build();

        ProjectEntity entity = this.projectEntityToDomainMapper.mapReverse(project);

        assertNull(entity.getId());
        assertEquals("abc@naver.com", entity.getCreatorEmail());
        assertEquals("홍길동", entity.getCreatorName());
        assertEquals("010-1234-1234", entity.getCreatorPhone());
        assertEquals(Project.Status.IN_PROGRESS, entity.getStatus());
        assertEquals(beginDate, entity.getBeginDate());
        assertEquals(dueDate, entity.getDueDate());
        assertTrue(1000L == entity.getSponsoredAmount());
        assertTrue(10 == entity.getSponsoredCount());
        assertEquals("타이틀", entity.getTitle());
        assertEquals("설명", entity.getDescription());
    }
}