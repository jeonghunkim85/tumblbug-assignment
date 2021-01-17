package com.tumblbug.assignment.core.applications;

import com.tumblbug.assignment.core.applications.port.out.ReadProjectPort;
import com.tumblbug.assignment.core.applications.port.out.WriteProjectPort;
import com.tumblbug.assignment.core.domains.Creator;
import com.tumblbug.assignment.core.domains.Project;
import com.tumblbug.assignment.core.infrastructures.entities.ProjectEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ReadProjectServiceTest {

    @Mock
    ReadProjectPort readProjectPort;

    @Mock
    WriteProjectPort writeProjectPort;

    @InjectMocks
    ReadProjectService readProjectService;


    LocalDateTime beginDate = LocalDateTime.of(2020, 1, 1, 0, 0, 0);
    LocalDateTime dueDate = LocalDateTime.of(2099, 1,1, 23, 59, 59);

    Project project1 = Project.builder()
            .id(UUID.randomUUID())
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

    Project project2 = Project.builder()
            .id(UUID.randomUUID())
            .beginDate(beginDate)
            .dueDate(dueDate)
            .sponsoredAmount(1000L)
            .sponsoredCount(10)
            .creator(Creator.builder()
                    .email("abd@naver.com")
                    .name("홍길순")
                    .phone("010-1234-4567")
                    .build())
            .title("타이틀2")
            .description("설명2")
            .targetAmount(10000L)
            .build();


    @Test
    public void getProjectListTest() {

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

        List<Project> projectList = Arrays.asList( project1, project2 );

        when(readProjectPort.findAll(any()))
                .thenReturn(new PageImpl<>(projectList));

        when(writeProjectPort.updateProject(any()))
                .thenReturn(null); // 결과값을 사용하지 않으므로 null return 해도 무방.

        Page<Project> result = this.readProjectService.getProjectList(param);

        verify(readProjectPort, times(1))
                .findAll(param);
        verify(writeProjectPort, times(2))
                .updateProject(any());

        assertTrue(result.getContent().size() == 2);
        assertEquals(projectList, result.getContent());
    }

    @Test
    public void getProjectTest() {

        UUID uuid = UUID.randomUUID();

        when(readProjectPort.findOneById(any()))
                .thenReturn(Optional.of(project1));

        when(writeProjectPort.updateProject(project1))
                .thenReturn(project1); // 결과값을 사용하지 않으므로 null return 해도 무방.

        Project result = this.readProjectService.getProject(uuid);

        verify(readProjectPort, times(1))
                .findOneById(uuid);
        verify(writeProjectPort, times(1))
                .updateProject(project1);

        assertEquals(result, project1);
    }
}