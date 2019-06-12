package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

public interface MealService {
    void createMeal(Meal meal);
    void deleteMeal(int id);
    void updateMeal(int id, Meal meal);
    Meal getMealById(int id);
}
