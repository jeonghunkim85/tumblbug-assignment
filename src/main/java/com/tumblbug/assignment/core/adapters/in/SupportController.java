package com.tumblbug.assignment.core.adapters.in;

import com.tumblbug.assignment.core.adapters.in.models.request.SupportRequestModel;
import com.tumblbug.assignment.core.applications.port.in.SupportUseCases;
import com.tumblbug.assignment.core.domains.Support;
import com.tumblbug.assignment.core.domains.exceptions.ProjectDomainException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SupportController {

    private final SupportUseCases supportUseCases;

    @PostMapping("projects/{id}/support")
    public void supportProject(@PathVariable String id, @RequestBody @Valid SupportRequestModel supportRequestModel) {

        UUID uuid = UUID.fromString(id);
        Support support = Support.builder()
                .projectId(uuid)
                .amount(supportRequestModel.getSponsoredAmount())
                .build();

        try {
            this.supportUseCases.doSupport(support);
        }catch(ProjectDomainException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
