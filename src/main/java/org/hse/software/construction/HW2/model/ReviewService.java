package org.hse.software.construction.HW2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewService {
    @JsonSerialize
    public ArrayList<Review> reviews = new ArrayList<>();
    public Dish mostPopularDish;
    public double averageRating;

    public void addReview(Review review) {
        if (reviews == null) {
            reviews = new ArrayList<>();
        }
        reviews.add(review);
    }

    public void removeReview(Review review) {
        if (reviews == null) {
            reviews = new ArrayList<>();
        }
        reviews.remove(review);
    }

    public Dish getMostPopularDish() {
        Map<Dish, Integer> dishRatings = new HashMap<>();
        Map<Dish, Integer> dishCounts = new HashMap<>();

        for (Review review : reviews) {
            for (Dish dish : review.getOrder().getDishes()) {
                int rating = review.getRating();

                dishRatings.put(dish, dishRatings.getOrDefault(dish, 0) + rating);
                dishCounts.put(dish, dishCounts.getOrDefault(dish, 0) + 1);
            }
        }

        Dish mostPopularDish = null;
        double highestAverageRating = 0;

        for (Map.Entry<Dish, Integer> entry : dishRatings.entrySet()) {
            Dish dish = entry.getKey();
            double averageRating = (double) entry.getValue() / dishCounts.get(dish);

            if (averageRating > highestAverageRating) {
                highestAverageRating = averageRating;
                mostPopularDish = dish;
            }
        }

        return mostPopularDish;
    }

    public double getAverageRating() {
        if (reviews.isEmpty()) {
            return 0;
        }

        int totalRating = 0;
        for (Review review : reviews) {
            totalRating += review.getRating();
        }

        return (double) totalRating / reviews.size();
    }
}