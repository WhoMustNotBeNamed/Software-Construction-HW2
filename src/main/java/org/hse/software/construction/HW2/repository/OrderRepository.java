package org.hse.software.construction.HW2.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.hse.software.construction.HW2.model.Menu;
import org.hse.software.construction.HW2.model.Order;

@Data
public class OrderRepository {
    private ObjectMapper objectMapper = new ObjectMapper();
    public void saveOrder(Order order, String path) {
        try {
            if (order == null) {
                return;
            }
            objectMapper.writeValue(new java.io.File(path), order);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

    }

    public Order restoreMenu(String path) {
        try {
            return objectMapper.readValue(new java.io.File(path), Order.class);
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
