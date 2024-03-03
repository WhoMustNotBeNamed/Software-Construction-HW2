package org.hse.software.construction.HW2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Snapshot {
    private Menu menu;
    private Account account;
    private MoneyStorage moneyStorage;
    private ReviewService reviewService;
}
