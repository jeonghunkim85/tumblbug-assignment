package com.tumblbug.assignment.core.adapters.in.models.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Max;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SupportRequestModel {

    @Max(value = 100000000L)
    Long sponsoredAmount; // 후원액
}
