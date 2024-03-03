package org.hse.software.construction.HW2.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    private String review;
    private int rating;
    private String author;
    private Order order;
}
