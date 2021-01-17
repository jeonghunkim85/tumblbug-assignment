package com.tumblbug.assignment.core.applications;

import com.tumblbug.assignment.core.applications.port.in.ReadProjectUseCases;
import com.tumblbug.assignment.core.applications.port.out.ReadProjectPort;
import com.tumblbug.assignment.core.domains.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReadProjectService implements ReadProjectUseCases {

    final ReadProjectPort readProjectPort;

    @Override
    public Page<Project> getProjectList(ReadProjectPort.ProjectQueryParams projectQueryParams) {
        return this.readProjectPort.findAll(projectQueryParams);
    }

    @Override
    public Optional<Project> getProject(UUID id) {
        return this.readProjectPort.findOneById(id);
    }
}
