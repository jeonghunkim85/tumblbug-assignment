package com.tumblbug.assignment.core.adapters.out;

import com.tumblbug.assignment.core.adapters.out.mappers.ProjectEntityToDomainMapper;
import com.tumblbug.assignment.core.applications.port.out.ReadProjectPort;
import com.tumblbug.assignment.core.domains.Project;
import com.tumblbug.assignment.core.infrastructures.repositories.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ReadProjectAdapter implements ReadProjectPort {

    final ProjectEntityToDomainMapper projectEntityToDomainMapper;
    final ProjectRepository projectRepository;

    @Override
    public Optional<Project> findOneById(UUID uuid) {
        return this.projectRepository.findById(uuid)
                .map(projectEntityToDomainMapper::map);
    }

    @Override
    public Page<Project> findAll(ProjectQueryParams projectQueryParams) {
        Pageable pageable = projectQueryParams.getPageable();
        return this.projectRepository.findAll(pageable)
                .map(projectEntityToDomainMapper::map);
    }
}
