# Diet Analysis Service

This is a Spring Boot application built with Java 21 that analyzes food consumption data.

## Prerequisites
- Java 21 SDK
- Maven

## How to Run
1. Clone the repository.
2. Navigate to the root folder.
3. Run: `./mvnw spring-boot:run`
4. Access endpoints at `localhost:8080`.

## Endpoints
1. **Average Calories (Purchased):** `GET /api/meals/analysis/average-calories`
2. **Top Low-Cal Favorites:** `GET /api/meals/analysis/low-cal-favorites`
3. **Best Protein Value:** `GET /api/meals/analysis/protein-value`
