package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );

       for (UserMealWithExceed usmwe : getFilteredWithExceeded(mealList,
               LocalDateTime.of(2015, 5, 30, 5, 0),
               LocalDateTime.of(2015, 5, 30, 20, 0), 1000))
           System.out.println(usmwe.toString());


    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalDateTime startTime, LocalDateTime endTime, int caloriesPerDay) {

        Optional<Integer> sumCalories = mealList.stream()
                .filter(x -> TimeUtil.isBetween(x.getDateTime(), startTime, endTime))
                .map(UserMeal::getCalories)
                .reduce(Integer::sum);

        return  mealList.stream()
                .filter(x -> TimeUtil.isBetween(x.getDateTime(), startTime, endTime))
                .collect(
                        () -> new ArrayList<UserMealWithExceed>(),
                        (list, item) -> list.add(new UserMealWithExceed(item.getDateTime(), item.getDescription(), item.getCalories(), sumCalories.isPresent() ? sumCalories.get() > caloriesPerDay : false)),
                        (list1, list2) -> list1.addAll(list2));


    }
}
