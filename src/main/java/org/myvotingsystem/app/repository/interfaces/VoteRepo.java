package org.myvotingsystem.app.repository.interfaces;

import org.myvotingsystem.app.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepo {
    Vote get(int id);
    Vote getByUserIdAndDate(int userId, LocalDate date);
    List<Vote> getAll();
    List<Vote> getAllByDate(LocalDate date);

    Vote save(Vote vote, int userId, int restaurantId);

    boolean delete(int id);
    void deleteAll();
}
