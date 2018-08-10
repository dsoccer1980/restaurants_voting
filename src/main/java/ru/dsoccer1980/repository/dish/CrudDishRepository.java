package ru.dsoccer1980.repository.dish;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.dsoccer1980.model.Dish;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudDishRepository extends JpaRepository<Dish, Integer> {

    @Override
    @Transactional
    Dish save(Dish dish);

    @Transactional
    @Modifying
    @Query("DELETE FROM Dish u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @Override
    Optional<Dish> findById(Integer id);

    @Override
    List<Dish> findAll();

    List<Dish> findDishByRestaurantIdAndDate(Integer id, LocalDate date);

}
