package com.tumblbug.assignment.core.applications.port.in;

import com.tumblbug.assignment.core.applications.port.out.ReadProjectPort;
import com.tumblbug.assignment.core.domains.Project;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface ReadProjectUseCases {

    // 리스트 조회
    Page<Project> getProjectList(ReadProjectPort.ProjectQueryParams projectQueryParams);

    // 단건 조회
    Optional<Project> getProject(UUID id);


}
