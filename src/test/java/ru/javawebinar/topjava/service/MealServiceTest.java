package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }
    @Autowired
    private MealService service;

    @Test
    public void get() {
        assertMatch(service.get(100003, 100000), MEAL_DINNER);
    }

    @Test
    public void delete() {
        service.delete(100002, 100000);
        assertMatch(service.getAll(100000), MEAL_SUPPER, MEAL_DINNER);
    }

    @Test
    public void getBetweenDates() {
        assertMatch(service.getBetweenDates(LocalDate.of(2019, 06,25),
                LocalDate.of(2019, 06,25), 100000), MEAL_SUPPER, MEAL_DINNER);
    }

    @Test
    public void getAll() {
        List<Meal> all = service.getAll(100000);
        assertMatch(all, MEAL_SUPPER, MEAL_DINNER, MEAL_BREAKFEST);

    }

    @Test
    public void update() {
        Meal newMeal = new Meal(MEAL_DINNER_ID, LocalDateTime.of(2019, 06, 25, 14, 00),
                "UpdatedDinner", 1300);
        service.update(newMeal, 100000);
        assertMatch(service.get(MEAL_DINNER_ID, 100000), newMeal);
    }

    @Test
    public void create() {
        Meal newMeal = new Meal(LocalDateTime.of(2019, 06, 25, 14, 00),
                "CreatedDinner", 1300);
        int newMealId = service.create(newMeal, 100000).getId();
        assertMatch(service.get(newMealId, 100000), newMeal);
    }

    @Test(expected = NotFoundException.class)
    public void deletedNotFound() throws Exception {
        service.delete(100003, 100001);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(100003, 100001);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound() throws Exception {
        Meal newMeal = new Meal(MEAL_DINNER_ID, LocalDateTime.of(2019, 06, 25, 14, 00),
                "UpdatedDinner", 1300);
        service.update(newMeal, 100001);
    }

    @Test(expected = DuplicateKeyException.class)
    public void duplicateTimeCreate() throws Exception {
        service.create(new Meal(LocalDateTime.of(2019, 06, 25, 19, 00),
                "TestMeal", 9999),
                100000);
    }



}