package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;

public interface MealService {
    void createMeal(LocalDateTime localDateTime, String description, int calories);
    void deleteMeal(int id);
    void updateMeal(int id, Meal meal);
    Meal getMealById(int id);
}
