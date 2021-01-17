package com.tumblbug.assignment.core.applications.port.out;

import com.tumblbug.assignment.core.domains.Project;

import java.util.UUID;

public interface WriteProjectPort {

    Project registerProject(Project project);

    Project updateProject(Project project);

    void deleteProject(UUID uuid);
}
