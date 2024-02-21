package org.hse.software.construction.HW2.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Data;
import org.hse.software.construction.HW2.model.Order;

import java.io.File;
import java.io.IOException;

@Data
@Builder
public class OrderMemento {
    private final Order state;
    private final ObjectMapper objectMapper;
    private final String filePath;

//    public OrderMemento(Order state, String filePath) {
//        this.state = new Order(state);
//        this.objectMapper = new ObjectMapper();
//        this.filePath = filePath;
//    }
//
//    public void saveToJson() {
//        try {
//            objectMapper.writeValue(new File(filePath), state);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public Order restoreFromJson() {
//        try {
//            return objectMapper.readValue(new File(filePath), Order.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}