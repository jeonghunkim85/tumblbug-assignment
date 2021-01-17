package com.tumblbug.assignment.core.adapters.in.models.request.mappers;

import com.tumblbug.assignment.commons.utils.BaseMapper;
import com.tumblbug.assignment.core.adapters.in.models.request.ProjectRequestModel;
import com.tumblbug.assignment.core.domains.Creator;
import com.tumblbug.assignment.core.domains.Project;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
public class ProjectRequestModelMapper extends BaseMapper<ProjectRequestModel, Project> {

    private final BaseMapper<ProjectRequestModel, Creator> projectToCreateorMapper = new BaseMapper<ProjectRequestModel, Creator>() {
        @Override
        protected void configMap(TypeMap<ProjectRequestModel, Creator> typeMap) {
            typeMap.addMappings(mapping -> {
                mapping.map(ProjectRequestModel::getCreatorPhone, Creator::setPhone);
                mapping.map(ProjectRequestModel::getCreatorEmail, Creator::setEmail);
                mapping.map(ProjectRequestModel::getCreatorName, Creator::setName);
            });
        }
    };

    @Override
    protected void configMap(TypeMap<ProjectRequestModel, Project> typeMap) {
        typeMap.addMappings(mapping -> {
            mapping.skip(Project::setId);
            mapping.using(ctx -> projectToCreateorMapper.map((ProjectRequestModel)ctx.getSource()))
                    .map(s->s, Project::setCreator);
        });
    }
}
