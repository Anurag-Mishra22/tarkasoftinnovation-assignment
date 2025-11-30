package com.tarkasoftinnovation.assignment.model;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record Meal(
        Integer id,

        // JSON has "user_id": "18", mapping it to String or Integer works
        @JsonProperty("user_id")
        String userId,

        // JSON has "age": "33", Jackson will automatically convert the string "33" to Integer
        Integer age,

        // JSON has "user_weight": "91.88", Jackson converts string to Double
        @JsonProperty("user_weight")
        Double userWeight,

        String name,
        Double price,
        Integer weight, // The weight of the food itself
        Integer calories,
        Double fat,
        Double carbs,
        Double protein,

        // Keeping as String is safest (e.g., "11:58").
        // You can parse it to LocalTime in your service when needed.
        @JsonProperty("time_consumed")
        String timeConsumed,

        @JsonProperty("date_consumed")
        LocalDate dateConsumed,

        String type, // lunch, snack, breakfast, dinner

        Boolean favorite, // Jackson handles "true"/"false" strings to Boolean automatically

        String procedence // purchased vs homemade
) {}
