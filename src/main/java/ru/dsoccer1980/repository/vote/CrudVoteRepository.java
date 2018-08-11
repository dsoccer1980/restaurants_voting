package ru.dsoccer1980.repository.vote;

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
    @Query("DELETE FROM Vote u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @Override
    Optional<Vote> findById(Integer id);

    @Override
    List<Vote> findAll();

    List<Vote> findByRestaurantIdAndDate(Integer id, LocalDate date);
}
