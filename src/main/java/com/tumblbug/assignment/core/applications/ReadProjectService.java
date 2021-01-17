package com.tumblbug.assignment.core.applications;

import com.tumblbug.assignment.core.applications.port.in.ReadProjectUseCases;
import com.tumblbug.assignment.core.applications.port.out.ReadProjectPort;
import com.tumblbug.assignment.core.applications.port.out.WriteProjectPort;
import com.tumblbug.assignment.core.domains.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReadProjectService implements ReadProjectUseCases {

    final ReadProjectPort readProjectPort;
    final WriteProjectPort writeProjectPort;

    @Override
    @Transactional
    public Page<Project> getProjectList(ReadProjectPort.ProjectQueryParams projectQueryParams) {
        Page<Project> projects = this.readProjectPort.findAll(projectQueryParams);
        // project 상태 업데이트
        projects.stream()
                .forEach(this.writeProjectPort::updateProject);
        return projects;
    }

    @Override
    @Transactional
    public Project getProject(UUID id) {
        Project project = this.readProjectPort.findOneById(id)
                .orElseThrow(() -> new NoSuchElementException("there is no project to get. id:"+id.toString()));
        writeProjectPort.updateProject(project);
        return project;
    }
}
