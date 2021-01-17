package com.tumblbug.assignment.core.adapters.in.models.response.mappers;

import com.tumblbug.assignment.commons.utils.BaseMapper;
import com.tumblbug.assignment.core.adapters.in.models.response.ProjectListResponseModel;
import com.tumblbug.assignment.core.domains.Project;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
public class ProjectListResponseModelMapper extends BaseMapper<Project, ProjectListResponseModel> {

    @Override
    protected void configMap(TypeMap<Project, ProjectListResponseModel> typeMap) {
        typeMap.addMappings(mapping -> {
            mapping.map(s -> s.getCreator().getName(), ProjectListResponseModel::setCreatorName);
            mapping.using(ctx -> ctx.getSource().toString())
                    .map(Project::getStatus, ProjectListResponseModel::setStatus);
        });
    }
}
