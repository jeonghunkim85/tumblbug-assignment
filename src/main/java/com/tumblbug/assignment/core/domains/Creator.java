package com.tumblbug.assignment.core.domains;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Creator {
    String name;
    String email;
    String phone;
}
