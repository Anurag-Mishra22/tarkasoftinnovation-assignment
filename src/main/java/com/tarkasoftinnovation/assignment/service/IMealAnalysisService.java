package com.tarkasoftinnovation.assignment.service;

import com.tarkasoftinnovation.assignment.dto.LowCalFavoriteDto;
import com.tarkasoftinnovation.assignment.dto.ProteinValueMealDto;

import java.util.List;

public interface IMealAnalysisService {
    Double getAverageCaloriesPurchased();
    List<LowCalFavoriteDto> getTopLowCalorieFavorites();
    List<ProteinValueMealDto> getBestValueProteinMeals();
}
