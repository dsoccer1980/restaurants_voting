package ru.dsoccer1980.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.dsoccer1980.model.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

    @Override
    @Transactional
    Vote save(Vote vote);

    @Transactional
    @Modifying
    @Query("DELETE FROM Vote u WHERE u.user.id=:userId and u.date=:date")
    int delete(@Param("userId") int userId, @Param("date") LocalDate date);

    @Override
    Optional<Vote> findById(Integer id);

    @Override
    List<Vote> findAll();

    List<Vote> findByRestaurantIdAndDate(Integer id, LocalDate date);

    @Query("SELECT v FROM Vote v JOIN FETCH v.restaurant WHERE v.user.id=:userId")
    List<Vote> findByUserId(@Param("userId") int userId);

    @Query("SELECT v FROM Vote v JOIN FETCH v.restaurant WHERE v.date=:date")
    List<Vote> findByDate(@Param("date") LocalDate date);

    Optional<Vote> findByUserIdAndDate(@Param("userId") int userId, @Param("date") LocalDate date);
}
