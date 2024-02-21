package org.hse.software.construction.HW2.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchConnectionDetails;

@Data
@Builder
public class User {
    private String username;
    private String password;
    private UserRole role;
}

