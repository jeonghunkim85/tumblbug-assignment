package com.tumblbug.assignment.core.adapters.out;

import com.tumblbug.assignment.core.adapters.out.mappers.ProjectEntityToDomainMapper;
import com.tumblbug.assignment.core.applications.port.out.WriteProjectPort;
import com.tumblbug.assignment.core.domains.Project;
import com.tumblbug.assignment.core.infrastructures.entities.ProjectEntity;
import com.tumblbug.assignment.core.infrastructures.repositories.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class WriteProjectAdapter implements WriteProjectPort {

    private final ProjectRepository projectRepository;
    private final ProjectEntityToDomainMapper projectEntityToDomainMapper;

    @Override
    public Project registerProject(Project project) {
        ProjectEntity projectEntity = this.projectEntityToDomainMapper.mapReverse(project);
        this.projectRepository.save(projectEntity);
        return this.projectEntityToDomainMapper.map(projectEntity);
    }

    @Override
    public Project updateProject(Project project) {
        ProjectEntity projectEntity = this.projectRepository.findById(project.getId())
                .orElseThrow(() -> new NoSuchElementException("there is no project to update. id:" + project.getId().toString() ));
        this.projectEntityToDomainMapper.setReverse(project, projectEntity);
        this.projectRepository.save(projectEntity);
        return this.projectEntityToDomainMapper.map(projectEntity);
    }

    @Override
    public void deleteProject(UUID uuid) {
        ProjectEntity projectEntity = this.projectRepository.findById(uuid)
                .orElseThrow(() -> new NoSuchElementException("there is no project to delete. id:" + uuid.toString()));
        this.projectRepository.delete(projectEntity);
    }
}
