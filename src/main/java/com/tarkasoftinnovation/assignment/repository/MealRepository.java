package com.tarkasoftinnovation.assignment.repository;

import com.tarkasoftinnovation.assignment.model.Meal;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import java.io.IOException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

@Component
public class MealRepository {
   private List<Meal> meals;
   private final ObjectMapper objectMapper;

    public MealRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    @PostConstruct
    public void loadData() {
        try (InputStream inputStream = getClass().getResourceAsStream("/data.json")) {
            // Requirement 1 & 2: Read and Parse
            this.meals = objectMapper.readValue(inputStream, new TypeReference<List<Meal>>() {});
            System.out.println("Data loaded successfully: " + meals.size() + " items.");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load JSON data", e);
        }
    }

    public List<Meal> getAllMeals() {
        return meals != null ? meals : Collections.emptyList();
    }
}
