package org.hse.software.construction.HW2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Dish {
    private String name;
    private int price;
    private int timeToCook;
    private int preparationDifficulty;
    private int availableQuantity;
}
