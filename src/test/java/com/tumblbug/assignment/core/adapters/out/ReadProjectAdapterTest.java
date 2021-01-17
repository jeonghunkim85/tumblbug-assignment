package com.tumblbug.assignment.core.adapters.out;

import com.tumblbug.assignment.core.adapters.out.mappers.ProjectEntityToDomainMapper;
import com.tumblbug.assignment.core.applications.port.out.ReadProjectPort;
import com.tumblbug.assignment.core.domains.Project;
import com.tumblbug.assignment.core.infrastructures.entities.ProjectEntity;
import com.tumblbug.assignment.core.infrastructures.repositories.ProjectRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ReadProjectAdapterTest {

    @Spy ProjectEntityToDomainMapper projectEntityToDomainMapper;
    @Mock ProjectRepository projectRepository;

    @InjectMocks
    ReadProjectAdapter readProjectAdapter;

    ProjectEntity projectEntity1;
    ProjectEntity projectEntity2;

    @Before
    public void init() {

        LocalDateTime beginDate = LocalDateTime.of(2020, 1, 1, 0, 0, 0);
        LocalDateTime dueDate = LocalDateTime.of(2099, 1,1, 23, 59, 59);

        projectEntity1 = ProjectEntity.builder()
                .id(UUID.randomUUID())
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

        projectEntity2 = ProjectEntity.builder()
                .id(UUID.randomUUID())
                .beginDate(beginDate)
                .dueDate(dueDate)
                .sponsoredAmount(1000L)
                .sponsoredCount(10)
                .creatorEmail("abd@naver.com")
                .creatorName("홍길순")
                .creatorPhone("010-1234-4567")
                .title("타이틀2")
                .description("설명2")
                .targetAmount(10000L)
                .build();

    }

    @Test
    public void findAllTest() {

        List<ProjectEntity> projectList = Arrays.asList(projectEntity1, projectEntity2);
        ReadProjectPort.ProjectQueryParams param = new ReadProjectPort.ProjectQueryParams() {
            public int getPageNumber() {
                return 0;
            }
            public int getPageSize() {
                return 10;
            }
            public SortBy getSortBy() {
                return SortBy.BEGIN_DATE;
            }
            public SortDirection getSortDirection() {
                return SortDirection.ASC;
            }
        };

        when(projectRepository.findAll(param.getPageable()))
                .thenReturn(new PageImpl<>(projectList));

        Page<Project> projectPage = this.readProjectAdapter.findAll(param);

        assertEquals(2, projectPage.getContent().size());

        verify(projectRepository, times(1))
                .findAll( param.getPageable() );

        verify(projectEntityToDomainMapper, times(2))
                .map(any());

    }
}