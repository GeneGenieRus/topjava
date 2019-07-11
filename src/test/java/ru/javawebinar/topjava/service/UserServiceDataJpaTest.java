package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.MealTestData.MEAL1;
import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles(value = "datajpa")
public class UserServiceDataJpaTest extends UserServiceTest{

    @Test
    public void getWithMeals() throws Exception {
        User user = service.getWithMeals(USER_ID);
        MealTestData.assertMatch(user.getMeals(), MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1);
    }
}
