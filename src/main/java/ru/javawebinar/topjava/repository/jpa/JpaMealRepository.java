package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaMealRepository implements MealRepository {


    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional

    public Meal save(Meal meal, int userId) {
        User ref = em.getReference(User.class, userId);
        meal.setUser(new User(ref));
        if (meal.isNew()){

            em.persist(meal);
            return meal;
        }
        else {
            Query query = em.createQuery("Update Meal m SET m.dateTime=:datetime, m.calories=:calories" +
                    ", m.description=:description WHERE m.id=:mealId AND m.user.id=:userId");
            if (query.setParameter("datetime", meal.getDateTime())
                    .setParameter("calories", meal.getCalories())
                    .setParameter("description", meal.getDescription())
                    .setParameter("userId", userId)
                    .setParameter("mealId", meal.getId())
                    .executeUpdate() != 0)
            return meal;
            else return null;
        }

    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
    /*   return em.createNamedQuery(Meal.DELETE, Meal.class).
                setParameter(1, id)
                .setParameter(2, userId)
                .executeUpdate() != 0;
*/

        Query query = em.createQuery("DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:userId");
        return query.setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    @Transactional
    public Meal get(int id, int userId) {
        List<Meal> list = em.createNamedQuery(Meal.GET, Meal.class)
                .setParameter(1, id)
                .setParameter(2, userId)
                .getResultList();
        if (list.size() > 0)
            return list.get(0);
        else
            return null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return em.createNamedQuery(Meal.ALL_SORTED, Meal.class)
                .setParameter(1, userId)
                .getResultList();
    }

    @Override
    @Transactional
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return
                em.createNamedQuery(Meal.BETWEEN, Meal.class)
                .setParameter(1, userId)
                .setParameter(2, startDate)
                .setParameter(3, endDate)
                .getResultList();
    }
}