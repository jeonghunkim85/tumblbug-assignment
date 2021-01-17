package com.tumblbug.assignment.core.adapters.in.models.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectResponseModel {

    String id;

    String title;

    String description;

    boolean published;

    String status;

    LocalDateTime beginDate;

    LocalDateTime dueDate;

    Long targetAmount;

    Long sponsoredAmount;

    Integer sponsoredCount;

    String creatorName;

    String creatorEmail;

    String creatorPhone;
}
