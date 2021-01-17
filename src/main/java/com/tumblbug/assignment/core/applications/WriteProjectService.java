package com.tumblbug.assignment.core.applications;

import com.tumblbug.assignment.core.applications.port.in.WriteProjectUseCases;
import com.tumblbug.assignment.core.applications.port.out.ReadProjectPort;
import com.tumblbug.assignment.core.applications.port.out.WriteProjectPort;
import com.tumblbug.assignment.core.domains.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WriteProjectService implements WriteProjectUseCases {

    final ReadProjectPort readProjectPort;
    final WriteProjectPort writeProjectPort;

    @Override
    public Project registerProject(Project project) {
        // todo. implement

        return null;
    }

    @Override
    public Project updateProject(Project project) {
        // todo. implement
        return null;
    }

    @Override
    public void deleteProject(Project project) {
        // todo. implement
    }
}
