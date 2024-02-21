package org.hse.software.construction.HW2.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Dish {
    private String name;
    private int price;
    private int timeToCook;
    private int preparationDifficulty;
    private int availableQuantity;
}
