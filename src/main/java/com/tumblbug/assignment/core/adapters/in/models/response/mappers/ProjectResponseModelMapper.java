package com.tumblbug.assignment.core.adapters.in.models.response.mappers;

import com.tumblbug.assignment.commons.utils.BaseMapper;
import com.tumblbug.assignment.commons.utils.Converters;
import com.tumblbug.assignment.core.adapters.in.models.response.ProjectResponseModel;
import com.tumblbug.assignment.core.domains.Project;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
public class ProjectResponseModelMapper extends BaseMapper<Project, ProjectResponseModel> {

    @Override
    protected void configMap(TypeMap<Project, ProjectResponseModel> typeMap) {
        typeMap.addMappings(mapping -> {
            mapping
                    .using(Converters.TO_STRING_CONVERTER)
                    .map(Project::getId, ProjectResponseModel::setId);

            mapping.map(s -> s.getCreator().getName(), ProjectResponseModel::setCreatorName);
            mapping.map(s -> s.getCreator().getPhone(), ProjectResponseModel::setCreatorPhone);
            mapping.map(s -> s.getCreator().getEmail(), ProjectResponseModel::setCreatorEmail);

            mapping.using(ctx -> ((Project.Status)ctx.getSource()).getDescription())
                    .map(Project::getStatus, ProjectResponseModel::setStatus);
        });

    }
}
