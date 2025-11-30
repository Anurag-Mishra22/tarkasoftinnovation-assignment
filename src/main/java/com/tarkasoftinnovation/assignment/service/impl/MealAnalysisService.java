package com.tarkasoftinnovation.assignment.service.impl;


import com.tarkasoftinnovation.assignment.dto.LowCalFavoriteDto;
import com.tarkasoftinnovation.assignment.dto.ProteinValueMealDto;
import com.tarkasoftinnovation.assignment.model.Meal;
import com.tarkasoftinnovation.assignment.repository.MealRepository;
import com.tarkasoftinnovation.assignment.service.IMealAnalysisService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MealAnalysisService implements IMealAnalysisService {

    private final MealRepository repository;

    public MealAnalysisService(MealRepository repository) {
        this.repository = repository;
    }

    // Requirement 3: Average calories of "purchased" food
    public Double getAverageCaloriesPurchased() {
        return repository.getAllMeals().stream()
                .filter(meal -> "purchased".equalsIgnoreCase(meal.procedence()))
                .mapToInt(Meal::calories)
                .average()
                .orElse(0.0);
    }

    // Requirement 4: Favorite dishes < 1000 cal, sorted desc, top 3
    public List<LowCalFavoriteDto> getTopLowCalorieFavorites() {
        return repository.getAllMeals().stream()
                .filter(Meal::favorite)
                .filter(meal -> meal.calories() < 1000)
                .sorted(Comparator.comparingInt(Meal::calories).reversed())
                .limit(3)
                .map(meal -> new LowCalFavoriteDto(
                        meal.name(),
                        // FIX IS HERE: Check if date is null first!
                        meal.dateConsumed() != null ? meal.dateConsumed().toString() : "Unknown Date",
                        meal.calories()))
                .collect(Collectors.toList());
    }

    // Requirement 5: Highest protein/cost ratio
    public List<ProteinValueMealDto> getBestValueProteinMeals() {
        return repository.getAllMeals().stream()
                .sorted(Comparator.comparingDouble((Meal m) -> m.protein() / m.price()).reversed())
                .limit(3)
                .map(m -> new ProteinValueMealDto(m.name(), m.protein() / m.price()))
                .collect(Collectors.toList());
    }


}