package com.tumblbug.assignment.core.applications;

import com.tumblbug.assignment.core.applications.port.in.SupportUseCases;
import com.tumblbug.assignment.core.applications.port.out.ReadProjectPort;
import com.tumblbug.assignment.core.applications.port.out.WriteProjectPort;
import com.tumblbug.assignment.core.domains.Project;
import com.tumblbug.assignment.core.domains.Support;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SupportService implements SupportUseCases {

    final ReadProjectPort readProjectPort;
    final WriteProjectPort writeProjectPort;

    @Override
    @Transactional
    public void doSupport(Support support) {
        Project project = this.readProjectPort.findOneById(support.getProjectId())
                .orElseThrow(() -> new NoSuchElementException("there is no project to support. id: "+ support.getProjectId().toString()));
        project.support(support.getAmount());
        writeProjectPort.updateProject(project);
    }
}
