package com.tumblbug.assignment.core.adapters.out;

import com.tumblbug.assignment.core.adapters.out.mappers.ProjectEntityToDomainMapper;
import com.tumblbug.assignment.core.domains.Creator;
import com.tumblbug.assignment.core.domains.Project;
import com.tumblbug.assignment.core.infrastructures.entities.ProjectEntity;
import com.tumblbug.assignment.core.infrastructures.repositories.ProjectRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WriteProjectAdapterTest {

    @Spy ProjectEntityToDomainMapper projectEntityToDomainMapper;
    @Mock ProjectRepository projectRepository;

    @InjectMocks WriteProjectAdapter writeProjectAdapter;

    @Test
    public void registerProjectTest() {
        UUID uuid = UUID.randomUUID();
        LocalDateTime beginDate = LocalDateTime.of(2020, 1, 1, 0, 0, 0);
        LocalDateTime dueDate = LocalDateTime.of(2099, 1,1, 23, 59, 59);

        Project project1 = Project.builder()
                .id(uuid)
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

        ProjectEntity projectEntity1 = ProjectEntity.builder()
                .beginDate(beginDate)
                .dueDate(dueDate)
                .sponsoredAmount(1000L)
                .sponsoredCount(10)
                .creatorEmail("abc@naver.com")
                .creatorName("홍길동")
                .creatorPhone("010-1234-1234")
                .title("타이틀")
                .status(Project.Status.IN_PROGRESS)
                .description("설명")
                .targetAmount(10000L)
                .build();

        when(projectRepository.save(projectEntity1))
                .thenReturn(projectEntity1);

        Project result = this.writeProjectAdapter.registerProject(project1);

        verify(projectRepository, times(1))
                .save(projectEntity1);
        verify(projectEntityToDomainMapper, times(1))
                .map(any());
        verify(projectEntityToDomainMapper, times(1))
                .mapReverse(any());
    }

    @Test
    public void updateProjectTest() {

        UUID uuid = UUID.randomUUID();
        LocalDateTime beginDate = LocalDateTime.of(2020, 1, 1, 0, 0, 0);
        LocalDateTime dueDate = LocalDateTime.of(2099, 1,1, 23, 59, 59);

        Project project1 = Project.builder()
                .id(uuid)
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

        ProjectEntity projectEntity1 = ProjectEntity.builder()
                .id(uuid)
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

        when(projectRepository.findById(uuid))
                .thenReturn(Optional.of(projectEntity1));
        when(projectRepository.save(any()))
                .thenReturn(projectEntity1);

        Project result = this.writeProjectAdapter.updateProject(project1);

        verify(projectRepository, times(1))
                .findById(uuid);
        verify(projectRepository, times(1))
                .save(projectEntity1);
        verify(projectEntityToDomainMapper, times(1))
                .setReverse(any(), any());
        verify(projectEntityToDomainMapper, times(1))
                .map(any());
    }


    @Test(expected = NoSuchElementException.class)
    public void updateProjectThrowNoSuchElementExceptionTest() {

        UUID uuid = UUID.randomUUID();
        LocalDateTime beginDate = LocalDateTime.of(2020, 1, 1, 0, 0, 0);
        LocalDateTime dueDate = LocalDateTime.of(2099, 1,1, 23, 59, 59);

        Project project1 = Project.builder()
                .id(uuid)
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

        when(projectRepository.findById(uuid))
                .thenReturn(Optional.empty());

        this.writeProjectAdapter.updateProject(project1);

        verify(projectRepository, times(1))
                .findById(uuid);
        verify(projectRepository, never())
                .save(any());
        verify(projectEntityToDomainMapper, never())
                .setReverse(any(), any());
        verify(projectEntityToDomainMapper, never())
                .map(any());
    }

    @Test
    public void deleteProjectTest() {
        UUID uuid = UUID.randomUUID();
        LocalDateTime beginDate = LocalDateTime.of(2020, 1, 1, 0, 0, 0);
        LocalDateTime dueDate = LocalDateTime.of(2099, 1,1, 23, 59, 59);

        ProjectEntity projectEntity1 = ProjectEntity.builder()
                .id(uuid)
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

        when(projectRepository.findById(uuid))
                .thenReturn(Optional.of(projectEntity1));
        doNothing().when(projectRepository).delete(projectEntity1);

        this.writeProjectAdapter.deleteProject(uuid);

        verify(projectRepository, times(1))
                .findById(uuid);
        verify(projectRepository, times(1))
                .delete(projectEntity1);
    }

    @Test(expected = NoSuchElementException.class)
    public void deleteProjectThrowsNoSuchElementExceptionTest() {
        UUID uuid = UUID.randomUUID();

        when(projectRepository.findById(uuid))
                .thenReturn(Optional.empty());

        this.writeProjectAdapter.deleteProject(uuid);
        verify(projectRepository, times(1))
                .findById(uuid);
        verify(projectRepository, never())
                .delete(any());
    }
}