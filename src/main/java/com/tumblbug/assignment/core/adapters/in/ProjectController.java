package com.tumblbug.assignment.core.adapters.in;

import com.tumblbug.assignment.core.adapters.in.models.response.ProjectListResponseModel;
import com.tumblbug.assignment.core.adapters.in.models.response.ProjectResponseModel;
import com.tumblbug.assignment.core.adapters.in.models.response.mappers.ProjectListResponseModelMapper;
import com.tumblbug.assignment.core.adapters.in.models.response.mappers.ProjectResponseModelMapper;
import com.tumblbug.assignment.core.applications.port.in.ReadProjectUseCases;
import com.tumblbug.assignment.core.applications.port.in.WriteProjectUseCases;
import com.tumblbug.assignment.core.applications.port.out.ReadProjectPort;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.http.HTTPException;
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

    @GetMapping("test")
    public String test() {
        return "it works!";
    }

    @Data
    public static class GetProjectListCommand implements ReadProjectPort.ProjectQueryParams {
        int pageNumber = 0;
        int pageSize = 10;
    }

    @GetMapping("projects")
    public Page<ProjectListResponseModel> getProjectList(@RequestParam GetProjectListCommand command) {
        log.debug("#getProjectList({})", command);
        return this.readProjectUseCases
                .getProjectList(command)
                .map(projectListResponseModelMapper::map);
    }

    @GetMapping("projects/{id}")
    public ProjectResponseModel getProject(@PathVariable String id) {
        log.debug("#getProject({})", id);
        if(id == null) {
            throw new HTTPException(HttpStatus.NOT_FOUND.value());
        }
        UUID uuid = UUID.fromString(id);
        return this.readProjectUseCases.getProject(uuid)
                .map(projectResponseModelMapper::map)
                .orElseThrow(() -> new HTTPException(HttpStatus.NOT_FOUND.value()));

    }

}
