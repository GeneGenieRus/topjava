package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static final int MEAL_BREAKFEST_ID = START_SEQ + 2;
    public static final int MEAL_DINNER_ID = START_SEQ + 3;
    public static final int MEAL_SUPPER_ID = START_SEQ + 4;

    public static final Meal MEAL_BREAKFEST = new Meal(MEAL_BREAKFEST_ID, LocalDateTime.of(2019, 06, 24, 10, 00), "Breakfest", 500);
    public static final Meal MEAL_DINNER = new Meal(MEAL_DINNER_ID, LocalDateTime.of(2019, 06, 25, 13, 00), "Dinner", 1500);
    public static final Meal MEAL_SUPPER = new Meal(MEAL_SUPPER_ID, LocalDateTime.of(2019, 06, 25, 19, 00), "Suppper", 1000);


    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "description", "calories");
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("description", "calories").isEqualTo(expected);
    }
    
}
