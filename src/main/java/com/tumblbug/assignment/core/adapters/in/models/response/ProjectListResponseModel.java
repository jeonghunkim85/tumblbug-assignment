package com.tumblbug.assignment.core.adapters.in.models.response;

import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
public class ProjectListResponseModel {

    String id;

    String title; // 제목

    String creatorName; // 창작자 이름

    Long targetAmount; // 목표액

    Integer sponsoredCount; // 후원수

    Long sponsoredAmount; // 후원액

    String status; // 프로젝트 상태

    LocalDateTime beginDate; // 시작일

    LocalDateTime dueDate; // 마감일
}
