package com.tarkasoftinnovation.assignment.dto;


// <T> allows this to hold ANY type of data (List, String, Meal, etc.)
public record ApiResponse<T>(
        int statusCode,
        String message,
        T data
) {
}
