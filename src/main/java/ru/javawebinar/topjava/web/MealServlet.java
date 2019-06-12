package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealServiceImp;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static MealServiceImp mealServiceImp = new MealServiceImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        if ((req.getContextPath() + "/deletemeal").equals(req.getRequestURI())) {
            log.debug("delete meal");
            mealServiceImp.deleteMeal(Integer.parseInt(req.getParameter("ID")));
            req.setAttribute("meals", MealsUtil.getFilteredWithExcess(MealsUtil.createList()
                    , LocalTime.MIN, LocalTime.MAX, 2000));
            req.getRequestDispatcher("meals.jsp").forward(req, resp);
        }

        else if   (req.getRequestURI().equals(req.getContextPath() + "/updatemeal")) {
            log.debug("update meal");
            Meal meal;

                meal = mealServiceImp.getMealById(Integer.parseInt(req.getParameter("ID")));
                if (meal != null) {
                req.setAttribute("meal", meal);
                req.setAttribute("date", meal.getDate().getDayOfMonth());
                req.setAttribute("month", meal.getDate().getMonthValue());
                req.setAttribute("year", meal.getDate().getYear());
                req.setAttribute("hour", meal.getTime().getHour());
                req.setAttribute("minute", meal.getTime().getMinute());

                }

                    req.setAttribute("id", Integer.parseInt(req.getParameter("ID")));

            req.getRequestDispatcher("mealsupdate.jsp").forward(req, resp);
        }

        else {
            log.debug("redirect to meals");
            req.setAttribute("meals", MealsUtil.getFilteredWithExcess(MealsUtil.createList()
                    , LocalTime.MIN, LocalTime.MAX, 2000));
            req.getRequestDispatcher("meals.jsp").forward(req, resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if (req.getParameter("id") != null)
            mealServiceImp.deleteMeal(Integer.parseInt(req.getParameter("id")));

        mealServiceImp.createMeal(new Meal(LocalDateTime.of(
                Integer.parseInt(req.getParameter("year")),
                Integer.parseInt(req.getParameter("month")),
                Integer.parseInt(req.getParameter("day")),
                Integer.parseInt(req.getParameter("hour")),
                Integer.parseInt(req.getParameter("minute"))),
                    req.getParameter("description"),
                        Integer.parseInt(req.getParameter("calories"))));

        resp.sendRedirect("meals");
    }
}
