package com.tumblbug.assignment.core.adapters.in.models.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectRequestModel {

    @NotBlank
    @Size(max = 50)
    @Pattern(regexp = "^[\\w\\s가-힣]+$")
    String title;

    @Size(max = 255)
    String description;

    boolean published = true;

    LocalDateTime beginDate;

    LocalDateTime dueDate;

    @Max(value = 100000000L)
    Long targetAmount;

    @NotBlank
    @Pattern(regexp = "^[\\w\\s가-힣_]+$")
    String creatorName;

    @NotBlank
    @Email
    String creatorEmail;

    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$")
    String creatorPhone;
}
