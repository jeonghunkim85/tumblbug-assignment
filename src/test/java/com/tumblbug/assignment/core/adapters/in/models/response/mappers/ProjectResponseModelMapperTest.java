package com.tumblbug.assignment.core.adapters.in.models.response.mappers;

import com.tumblbug.assignment.core.adapters.in.models.response.ProjectResponseModel;
import com.tumblbug.assignment.core.domains.Creator;
import com.tumblbug.assignment.core.domains.Project;
import junit.framework.TestCase;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ProjectResponseModelMapperTest extends TestCase {

    ProjectResponseModelMapper mapper = new ProjectResponseModelMapper();

    @Test
    public void test() {
        UUID uuid = UUID.randomUUID();
        LocalDateTime dueDate = LocalDateTime.of(2021, 10, 1, 12, 00, 00);
        LocalDateTime beginDate = LocalDateTime.of(2021, 1, 1, 00, 00, 00);

        Project project = Project.builder()
                .id(uuid)
                .title("타이틀")
                .description("설명")
                .targetAmount(100000L)
                .sponsoredAmount(1000L)
                .dueDate(dueDate)
                .beginDate(beginDate)
                .sponsoredCount(10)
                .published(true)
                .creator(Creator.builder()
                        .name("홍길동")
                        .phone("010-1234-5678")
                        .email("gildong@email.com")
                        .build())
                .build();

        ProjectResponseModel responseModel = this.mapper.map(project);

        assertThat(responseModel, allOf(
            hasProperty("id", is(uuid.toString())),
            hasProperty("title", is("타이틀")),
            hasProperty("description", is("설명")),
            hasProperty("targetAmount", is(100000L)),
            hasProperty("sponsoredAmount", is(1000L)),
            hasProperty("dueDate", is(dueDate)),
            hasProperty("beginDate", is(beginDate)),
            hasProperty("sponsoredCount", is(10)),
            hasProperty("published", is(true)),
            hasProperty("creatorName", is("홍길동")),
            hasProperty("creatorPhone", is("010-1234-5678")),
            hasProperty("creatorEmail", is("gildong@email.com"))
        ));
    }

}