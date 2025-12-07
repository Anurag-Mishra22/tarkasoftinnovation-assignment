
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

## Running Tests
Unit tests are included for the Service layer logic using JUnit 5 and Mockito.

To run the tests, execute:

`./mvnw test`

# File and Folder Structure

<img width="1205" height="1250" alt="image" src="https://github.com/user-attachments/assets/d78d94cb-ece1-4127-9798-7c7e240ccd73" />


# API Documentation
A Postman collection is included in this repository for easy testing.

**File:** `Tarkasoft_Assignment.postman_collection.json`  
**Import:** Open Postman -> Import -> Upload this file.



# Endpoints

## 1. Get Average Calories (Purchased Meals)
Calculates the average calories of all meals with procedence `"purchased"`.

**URL:** `GET /api/meals/analysis/average-calories`

### Response:
```json
{
    "statusCode": 200,
    "message": "Average calories calculated successfully",
    "data": {
        "average_calories": "349.06"
    }
}
````

---

## 2. Get Top Low-Calorie Favorites

Returns the top 3 favorite dishes under 1000 calories, sorted by calories (descending).

**URL:** `GET /api/meals/analysis/low-cal-favorites`

### Response:

```json
{
    "statusCode": 200,
    "message": "Top 3 low-calorie favorites retrieved",
    "data": [
        {
            "foodName": "Poke",
            "dateConsumed": "2022-09-26",
            "calories": 600
        },
        {
            "foodName": "Pho",
            "dateConsumed": "2022-11-12",
            "calories": 599
        },
        {
            "foodName": "Seafood Paella",
            "dateConsumed": "2022-11-22",
            "calories": 599
        }
    ]
}
```

---

## 3. Get Best Protein Value Meals

Returns the top 3 meals with the highest protein-to-price ratio.

**URL:** `GET /api/meals/analysis/protein-value`

### Response:

```json
{
    "statusCode": 200,
    "message": "Best protein value meals retrieved",
    "data": [
        {
            "mealName": "Hummus",
            "proteinToDollarRatio": 3.881553398058252
        },
        {
            "mealName": "Caprese Salad",
            "proteinToDollarRatio": 3.790513833992095
        },
        {
            "mealName": "Seafood Paella",
            "proteinToDollarRatio": 3.7563352826510723
        }
    ]
}
```
