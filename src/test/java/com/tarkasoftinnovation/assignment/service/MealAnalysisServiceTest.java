package com.tarkasoftinnovation.assignment.service;

import com.tarkasoftinnovation.assignment.service.impl.MealAnalysisService;

import com.tarkasoftinnovation.assignment.dto.LowCalFavoriteDto;
import com.tarkasoftinnovation.assignment.dto.ProteinValueMealDto;
import com.tarkasoftinnovation.assignment.model.Meal;
import com.tarkasoftinnovation.assignment.repository.MealRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MealAnalysisServiceTest {

    @Mock
    private MealRepository repository;

    @InjectMocks
    private MealAnalysisService service;

    private List<Meal> mockMeals;

    @BeforeEach
    void setUp() {
        mockMeals = Arrays.asList(
                // Meal 1: Purchased, Low Cal, Favorite
                new Meal(1, "1", 30, 70.0, "Healthy Salad", 10.0, 200, 300,
                        5.0, 10.0, 20.0, "12:00",
                        LocalDate.of(2023, 1, 1), "lunch", true, "purchased"),

                // Meal 2: Homemade, High Cal, Favorite (Excluded in low-cal test)
                new Meal(2, "1", 30, 70.0, "Big Burger", 5.0, 300, 1200,
                        20.0, 50.0, 30.0, "18:00",
                        LocalDate.of(2023, 1, 2), "dinner", true, "homemade"),

                // Meal 3: Purchased, High Cal, Not Favorite
                new Meal(3, "1", 30, 70.0, "Fancy Steak", 50.0, 400, 800,
                        15.0, 5.0, 40.0, "19:00",
                        LocalDate.of(2023, 1, 3), "dinner", false, "purchased"),

                // Meal 4: Purchased, Low Cal, Favorite (High protein ratio)
                new Meal(4, "1", 30, 70.0, "Protein Shake", 2.0, 100, 150,
                        1.0, 5.0, 30.0, "08:00",
                        LocalDate.of(2023, 1, 4), "breakfast", true, "purchased"));
    }

    @Test
    void shouldCalculateAverageCaloriesForPurchasedMeals() {
        // Arrange
        when(repository.getAllMeals()).thenReturn(mockMeals);

        // Act
        Double avg = service.getAverageCaloriesPurchased();

        // Expected:
        // Purchased meals: 300 (Meal 1), 800 (Meal 3), 150 (Meal 4)
        // Total = 1250, Count = 3 => Avg = 416.6666666666667

        // Assert
        assertEquals(416.6666666666667, avg);
    }

    @Test
    void shouldReturnTopLowCalorieFavoriteMeals() {
        // Arrange
        when(repository.getAllMeals()).thenReturn(mockMeals);

        // Act
        List<LowCalFavoriteDto> result = service.getTopLowCalorieFavorites();

        // Expected:
        // Favorites: Meal 1 (300), Meal 2 (1200), Meal 4 (150)
        // Filter < 1000 → Meal 1, Meal 4
        // Sorted desc → (300), (150)

        // Assert
        assertEquals(2, result.size());
        assertEquals("Healthy Salad", result.get(0).foodName());
        assertEquals("Protein Shake", result.get(1).foodName());
    }

    @Test
    void shouldReturnTopThreeMealsByProteinValue() {
        // Arrange
        when(repository.getAllMeals()).thenReturn(mockMeals);

        // Act
        List<ProteinValueMealDto> result = service.getBestValueProteinMeals();

        // Protein/Price ratios:
        // Meal 1 → 2.0
        // Meal 2 → 6.0
        // Meal 3 → 0.8
        // Meal 4 → 15.0

        // Expected order: Meal 4, Meal 2, Meal 1

        // Assert
        assertEquals(3, result.size());

        assertEquals("Protein Shake", result.get(0).mealName());
        assertEquals(15.0, result.get(0).proteinToDollarRatio(), 0.01);

        assertEquals("Big Burger", result.get(1).mealName());
        assertEquals(6.0, result.get(1).proteinToDollarRatio(), 0.01);

        assertEquals("Healthy Salad", result.get(2).mealName());
        assertEquals(2.0, result.get(2).proteinToDollarRatio(), 0.01);
    }

    @Test
    void shouldReturnZeroWhenNoPurchasedMealsExist() {
        when(repository.getAllMeals()).thenReturn(List.of());

        Double avg = service.getAverageCaloriesPurchased();

        assertEquals(0.0, avg);
    }
}
