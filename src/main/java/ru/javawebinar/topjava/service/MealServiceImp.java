package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;

public class MealServiceImp implements MealService {

    @Override
    public void createMeal(Meal meal) {

        MealsUtil.meals.add(meal);
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
