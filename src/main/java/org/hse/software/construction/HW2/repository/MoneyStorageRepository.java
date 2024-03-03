package org.hse.software.construction.HW2.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.hse.software.construction.HW2.model.MoneyStorage;

@Data
public class MoneyStorageRepository {
    private ObjectMapper objectMapper = new ObjectMapper();

    // Метод для сохранения кассы
    public void saveCashRegister(MoneyStorage moneyStorage, String path) {
        try {
            if (moneyStorage == null) {
                return;
            }
            objectMapper.writeValue(new java.io.File(path), moneyStorage);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для восстановления кассы
    public MoneyStorage restoreCashRegister(String path) {
        try {
            return objectMapper.readValue(new java.io.File(path), MoneyStorage.class);
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
