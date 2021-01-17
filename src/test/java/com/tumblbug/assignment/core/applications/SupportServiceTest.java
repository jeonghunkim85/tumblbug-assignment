package com.tumblbug.assignment.core.applications;

import com.tumblbug.assignment.core.applications.port.out.ReadProjectPort;
import com.tumblbug.assignment.core.applications.port.out.WriteProjectPort;
import com.tumblbug.assignment.core.domains.Creator;
import com.tumblbug.assignment.core.domains.Project;
import com.tumblbug.assignment.core.domains.Support;
import com.tumblbug.assignment.core.domains.exceptions.MaxAmountOverException;
import com.tumblbug.assignment.core.domains.exceptions.MaxSupportCountOverException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SupportServiceTest {

    @Mock ReadProjectPort readProjectPort;
    @Mock WriteProjectPort writeProjectPort;

    @InjectMocks SupportService supportService;

    LocalDateTime beginDate = LocalDateTime.of(2020, 1, 1, 0, 0, 0);
    LocalDateTime dueDate = LocalDateTime.of(2099, 1,1, 23, 59, 59);

    Project project1;

    @Before
    public void init() {
        this.project1 = Project.builder()
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
    }

    @Test
    public void doSupportHappycaseTest(){

        UUID uuid = UUID.randomUUID();
        when(readProjectPort.findOneById(uuid))
                .thenReturn(Optional.of(project1));

        when(writeProjectPort.updateProject(project1))
                .thenReturn(project1);

        this.supportService.doSupport(Support.builder().projectId(uuid).amount(100L).build());

        verify(readProjectPort, times(1))
                .findOneById(uuid);
        verify(writeProjectPort, times(1))
                .updateProject(project1);

    }

    @Test(expected = MaxAmountOverException.class)
    public void doSupport_when_amout_is_over_then_throws_MaxAmountException() {
        UUID uuid = UUID.randomUUID();
        when(readProjectPort.findOneById(uuid))
                .thenReturn(Optional.of(project1));

        when(writeProjectPort.updateProject(project1))
                .thenReturn(project1);

        this.project1.setSponsoredAmount(99999999L);

        this.supportService.doSupport(Support.builder().projectId(uuid).amount(2L).build());

        verify(readProjectPort, times(1))
                .findOneById(uuid);
        verify(writeProjectPort, never())
                .updateProject(any());
    }

    @Test(expected = MaxSupportCountOverException.class)
    public void doSupport_when_count_is_over_then_throws_MaxSupportCountOverException() {
        UUID uuid = UUID.randomUUID();
        when(readProjectPort.findOneById(uuid))
                .thenReturn(Optional.of(project1));

        when(writeProjectPort.updateProject(project1))
                .thenReturn(project1);

        this.project1.setSponsoredCount(100000);

        this.supportService.doSupport(Support.builder().projectId(uuid).amount(2L).build());

        verify(readProjectPort, times(1))
                .findOneById(uuid);
        verify(writeProjectPort, never())
                .updateProject(any());
    }

    @Test(expected = NoSuchElementException.class)
    public void doSupport_when_project_not_found() {
        UUID uuid = UUID.randomUUID();
        when(readProjectPort.findOneById(uuid))
                .thenReturn(Optional.empty());

        when(writeProjectPort.updateProject(project1))
                .thenReturn(project1);

        this.project1.setSponsoredAmount(99999999L);

        this.supportService.doSupport(Support.builder().projectId(uuid).amount(2L).build());

        verify(readProjectPort, times(1))
                .findOneById(uuid);

        verify(writeProjectPort, never())
                .updateProject(any());
    }


}