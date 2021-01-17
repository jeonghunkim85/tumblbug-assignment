package com.tumblbug.assignment.core.adapters.in;

import com.tumblbug.assignment.core.adapters.in.models.request.ProjectRequestModel;
import com.tumblbug.assignment.core.adapters.in.models.request.mappers.ProjectRequestModelMapper;
import com.tumblbug.assignment.core.adapters.in.models.response.ProjectListResponseModel;
import com.tumblbug.assignment.core.adapters.in.models.response.ProjectResponseModel;
import com.tumblbug.assignment.core.adapters.in.models.response.mappers.ProjectListResponseModelMapper;
import com.tumblbug.assignment.core.adapters.in.models.response.mappers.ProjectResponseModelMapper;
import com.tumblbug.assignment.core.applications.port.in.ReadProjectUseCases;
import com.tumblbug.assignment.core.applications.port.in.WriteProjectUseCases;
import com.tumblbug.assignment.core.applications.port.out.ReadProjectPort;
import com.tumblbug.assignment.core.domains.Project;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.NoSuchElementException;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProjectController {

    private final ReadProjectUseCases readProjectUseCases;
    private final WriteProjectUseCases writeProjectUseCases;

    private final ProjectResponseModelMapper projectResponseModelMapper;
    private final ProjectListResponseModelMapper projectListResponseModelMapper;

    private final ProjectRequestModelMapper projectRequestModelMapper;

    @Data
    public class GetProjectListCommand implements ReadProjectPort.ProjectQueryParams {
        SortBy sortBy = SortBy.BEGIN_DATE;
        SortDirection sortDirection = SortDirection.ASC;
        int pageNumber = 0;
        int pageSize = 10;
    }

    @GetMapping("projects")
    public Page<ProjectListResponseModel> getProjectList(GetProjectListCommand command) {
        log.debug("#getProjectList({})", command);
        return this.readProjectUseCases
                .getProjectList(command)
                .map(projectListResponseModelMapper::map);
    }

    @GetMapping("projects/{id}")
    public ProjectResponseModel getProject(@PathVariable String id) {
        log.debug("#getProject({})", id);
        UUID uuid = UUID.fromString(id);
        try{
            Project project = this.readProjectUseCases.getProject(uuid);
            return this.projectResponseModelMapper.map(project);
        }catch (NoSuchElementException nse) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("projects")
    public String insertProject(@RequestBody @Valid ProjectRequestModel projectRequestModel){
        log.debug("#insertProject({})", projectRequestModel);
        Project project = this.projectRequestModelMapper.map(projectRequestModel);
        project = this.writeProjectUseCases.registerProject(project);
        log.debug("inserted: {}", project);
        return project.getId().toString();
    }

    @PatchMapping("projects/{id}")
    public ProjectResponseModel updateProject(@PathVariable String id, @RequestBody @Valid ProjectRequestModel projectRequestModel) {
        log.debug("#updateProject({}, {})", id, projectRequestModel);
        UUID uuid = UUID.fromString(id);
        Project project = this.projectRequestModelMapper.map(projectRequestModel);
        project.setId(uuid);
        project = this.writeProjectUseCases.updateProject(project);
        log.debug("#updatedProject: {}", project);
        return this.projectResponseModelMapper.map(project);
    }

    @DeleteMapping("projects/{id}")
    public void deleteProject(@PathVariable String id) {
        log.debug("#deleteProject({})", id);
        UUID uuid = UUID.fromString(id);
        try {
            this.writeProjectUseCases.deleteProject(uuid);
        }catch (NoSuchElementException nse) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
