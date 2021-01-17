package com.tumblbug.assignment.core.domains;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Support {

    UUID projectId;
    Long amount;
}
