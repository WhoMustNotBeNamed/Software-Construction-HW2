package org.hse.software.construction.HW2.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.hse.software.construction.HW2.model.ReviewService;

@Data
public class ReviewRepository {
    private ObjectMapper objectMapper = new ObjectMapper();

    // Метод для сохранения отзыва
    public void saveReview(ReviewService review, String path) {
        try {
            if (review == null) {
                return;
            }
            objectMapper.writeValue(new java.io.File(path), review);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

    }

    // Метод для восстановления отзыва
    public ReviewService restoreReview(String path) {
        try {
            return objectMapper.readValue(new java.io.File(path), ReviewService.class);
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
