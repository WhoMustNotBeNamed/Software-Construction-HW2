package org.hse.software.construction.HW2.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MoneyStorage {
    private int cash;
    private int nonCash;
    private int totalMoney;


    public void addCash(int cash) {
        this.cash += cash;
    }

    public void addNonCash(int nonCash) {
        this.nonCash += nonCash;
    }

    public int getTotalMoney() {
        return cash + nonCash;
    }
}
