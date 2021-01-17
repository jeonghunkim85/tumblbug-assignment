package com.tumblbug.assignment.core.applications;

import com.tumblbug.assignment.core.applications.port.in.WriteProjectUseCases;
import com.tumblbug.assignment.core.applications.port.out.ReadProjectPort;
import com.tumblbug.assignment.core.applications.port.out.WriteProjectPort;
import com.tumblbug.assignment.core.domains.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WriteProjectService implements WriteProjectUseCases {

    final ReadProjectPort readProjectPort;
    final WriteProjectPort writeProjectPort;

    @Override
    @Transactional
    public Project registerProject(Project project) {
        return this.writeProjectPort.registerProject(project);
    }

    @Override
    @Transactional
    public Project updateProject(Project project) {
        return this.writeProjectPort.updateProject(project);
    }

    @Override
    @Transactional
    public void deleteProject(UUID id) {
        this.writeProjectPort.deleteProject(id);
    }
}
