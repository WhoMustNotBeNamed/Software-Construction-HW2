package org.hse.software.construction.HW2.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Dish {
    private String name;
    private int price;
    private int timeToCook;
    //private int preparationDifficulty;
    private int availableQuantity;
}
