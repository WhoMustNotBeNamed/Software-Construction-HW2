package org.hse.software.construction.HW2.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.hse.software.construction.HW2.model.Account;

@Data
public class AccountRepository {
    private ObjectMapper objectMapper = new ObjectMapper();

    // Метод для сохранения аккаунта
    public void saveAccount(Account account, String path) {
        try {
            if (account == null) {
                return;
            }
            objectMapper.writeValue(new java.io.File(path), account);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

    }

    // Метод для восстановления аккаунта
    public Account restoreUser(String path) {
        try {
            return objectMapper.readValue(new java.io.File(path), Account.class);
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
