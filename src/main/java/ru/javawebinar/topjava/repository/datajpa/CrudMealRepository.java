package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

   // Meal getMealByIdAndUserId(int id, int userId);

  //  List<Meal> getMealsByUserIdSorted(int userId, Sort SORT_DateTime_Description);

 //   @Query("SELECT m FROM Meal m WHERE m.user.id=:userId AND m.dateTime > :startDate AND  m.dateTime < :endDate ORDER BY m.dateTime DESC")
   List<Meal> getMealsByUserIdAndDateTimeBetweenOrderByDateTimeDescDescriptionAsc(int UserId,
                                                  LocalDateTime startDate,
                                                LocalDateTime endDate);
    Meal getMealByIdAndUserId(int id, int userId);

    List<Meal> getMealsByUserIdOrderByDateTimeDescDescriptionAsc(int userId);

    @Query("Select m From Meal m Join Fetch m.user u Where m.id=:id And m.user.id=:userId")
    Meal getWithUser(@Param("id") int id, @Param("userId") int userId);
}
