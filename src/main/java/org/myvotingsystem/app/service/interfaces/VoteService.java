package org.myvotingsystem.app.service.interfaces;

import org.myvotingsystem.app.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteService {
    Vote create(Vote vote, int userId, int restaurantId);

    Vote get(int id);
    List<Vote> getAll();
    List<Vote> getAllByDate(LocalDate date);
    Vote getByUserIdAndDate(int userId, LocalDate date);

    void delete(int id);
    void deleteAll();
}
