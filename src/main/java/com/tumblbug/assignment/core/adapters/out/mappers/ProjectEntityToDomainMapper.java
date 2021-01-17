package com.tumblbug.assignment.core.adapters.out.mappers;

import com.tumblbug.assignment.commons.utils.BaseMapper;
import com.tumblbug.assignment.core.domains.Creator;
import com.tumblbug.assignment.core.domains.Project;
import com.tumblbug.assignment.core.infrastructures.entities.ProjectEntity;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
public class ProjectEntityToDomainMapper extends BaseMapper<ProjectEntity, Project> {

    private final BaseMapper<ProjectEntity, Creator> projectToCreateorMapper = new BaseMapper<ProjectEntity, Creator>(){
        @Override
        protected void configMap(TypeMap<ProjectEntity, Creator> typeMap) {
            typeMap.addMappings(mapping -> {
                mapping.map(ProjectEntity::getCreatorPhone, Creator::setPhone);
                mapping.map(ProjectEntity::getCreaterEmail, Creator::setEmail);
                mapping.map(ProjectEntity::getCreatorName, Creator::setName);
            });
        }
    };

    @Override
    protected void configMap(TypeMap<ProjectEntity, Project> typeMap) {
        typeMap.addMappings(mapping -> {
            mapping
                .using(ctx -> projectToCreateorMapper.map((ProjectEntity) ctx.getSource()))
                .map(s -> s, Project::setCreator);

        });
    }

    @Override
    protected void configMapReverse(TypeMap<Project, ProjectEntity> typeMap) {
        typeMap.addMappings(mapping -> {
            mapping.map(s -> s.getCreator().getName(), ProjectEntity::setCreatorName);
            mapping.map(s -> s.getCreator().getEmail(), ProjectEntity::setCreaterEmail);
            mapping.map(s -> s.getCreator().getPhone(), ProjectEntity::setCreatorPhone);
        });
    }
}
