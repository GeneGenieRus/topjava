package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;

import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL_ID;
import static ru.javawebinar.topjava.UserTestData.ADMIN;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;

@ActiveProfiles(value = "datajpa")
public class MealServiceDataJpaTest extends MealServiceTest {


    @Test
    public void getWithUser() throws Exception{
        Meal meal = service.getWithUser(ADMIN_MEAL_ID, ADMIN_ID);
        UserTestData.assertMatch(meal.getUser(), ADMIN);
    }

}
