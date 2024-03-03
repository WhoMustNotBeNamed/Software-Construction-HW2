package org.hse.software.construction.HW2.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.hse.software.construction.HW2.model.Menu;

@Data
public class MenuRepository {
    private ObjectMapper objectMapper = new ObjectMapper();

    // Метод для сохранения меню
    public void saveMenu(Menu menu, String path) {
        try {
            if (menu == null) {
                return;
            }
            objectMapper.writeValue(new java.io.File(path), menu);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

    }

    // Метод для восстановления меню
    public Menu restoreMenu(String path) {
        try {
            return objectMapper.readValue(new java.io.File(path), Menu.class);
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
