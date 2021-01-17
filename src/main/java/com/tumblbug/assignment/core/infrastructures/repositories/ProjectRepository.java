package com.tumblbug.assignment.core.infrastructures.repositories;

import com.tumblbug.assignment.core.infrastructures.entities.ProjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjectRepository extends JpaRepository<ProjectEntity, UUID> {

    Page<ProjectEntity> findAll(Pageable pageable);
}
