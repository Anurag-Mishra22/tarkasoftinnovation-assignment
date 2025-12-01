package com.tarkasoftinnovation.assignment.controller;

import com.tarkasoftinnovation.assignment.dto.ApiResponse;
import com.tarkasoftinnovation.assignment.dto.LowCalFavoriteDto;
import com.tarkasoftinnovation.assignment.dto.ProteinValueMealDto;
import com.tarkasoftinnovation.assignment.service.IMealAnalysisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/meals")
public class MealController {

    private final IMealAnalysisService service;

    public MealController(IMealAnalysisService service) {
        this.service = service;
    }

    // Endpoint 1: Average Calories
    @GetMapping("/analysis/average-calories")
    public ResponseEntity<ApiResponse<Map<String, String>>> getAverageCalories() {
        Double rawAvg = service.getAverageCaloriesPurchased();
        String formatted = String.format("%.2f", rawAvg);

        // We wrap the single value in a Map so it looks like JSON data
        Map<String, String> data = Map.of("average_calories", formatted);

        ApiResponse<Map<String, String>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Average calories calculated successfully",
                data);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // Endpoint 2: Low Calorie Favorites
    @GetMapping("/analysis/low-cal-favorites")
    public ResponseEntity<ApiResponse<List<LowCalFavoriteDto>>> getLowCalFavorites() {
        List<LowCalFavoriteDto> result = service.getTopLowCalorieFavorites();
        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(
                    HttpStatus.NO_CONTENT.value(),
                    "No Favorite meals under 1000 calories",
                    result));
        }
        ApiResponse<List<LowCalFavoriteDto>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Top 3 low-calorie favorites retrieved",
                result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // Endpoint 3: Protein Value
    @GetMapping("/analysis/protein-value")
    public ResponseEntity<ApiResponse<List<ProteinValueMealDto>>> getProteinValue() {
        List<ProteinValueMealDto> result = service.getBestValueProteinMeals();
        ApiResponse<List<ProteinValueMealDto>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Best protein value meals retrieved",
                result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
