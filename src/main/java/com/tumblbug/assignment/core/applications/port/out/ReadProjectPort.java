package com.tumblbug.assignment.core.applications.port.out;

import com.tumblbug.assignment.core.domains.Project;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface ReadProjectPort {

    Optional<Project> findOneById(UUID uuid);

    Page<Project> findAll(ProjectQueryParams projectQueryParams);

    // todo. add sorting
    interface ProjectQueryParams {
        int getPageNumber();
        int getPageSize();
    }
}
