package com.tumblbug.assignment.core.applications.port.in;

import com.tumblbug.assignment.core.domains.Project;

public interface WriteProjectUseCases {

    Project registerProject(Project project);

    Project updateProject(Project project);

    void deleteProject(Project project);

}
