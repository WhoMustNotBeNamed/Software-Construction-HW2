package org.hse.software.construction.HW2.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String username;
    private String password;
    private UserRole role;
}

