package com.exmple.webflux.user.domain;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleModel implements Serializable {
    @Id
    String id;
    String code;
    String description;
}