package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MealServiceImp implements MealService {
    private static AtomicInteger generateID = new AtomicInteger(0);
    @Override
    public void createMeal(LocalDateTime localDateTime, String description, int calories) {

        MealsUtil.meals.add(new Meal(localDateTime, description, calories, generateID.incrementAndGet()));
    }

    @Override
    public void deleteMeal(int id) {
        for (Meal m : MealsUtil.meals)
            if (m.getID() == id)
            {
                MealsUtil.meals.remove(m);
                break;
            }
    }

    @Override
    public void updateMeal(int id, Meal meal) {

    }

    @Override
    public Meal getMealById(int id) {
        for (Meal m : MealsUtil.meals)
            if (m.getID() == id)
            {
               return m;
            }
        return null;
    }
}
