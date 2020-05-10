package org.myvotingsystem.app.service.implementations;

import org.myvotingsystem.app.model.Vote;
import org.myvotingsystem.app.repository.interfaces.VoteRepo;
import org.myvotingsystem.app.service.interfaces.VoteService;
import org.myvotingsystem.app.util.ValidationUtil;
import org.myvotingsystem.app.util.exception.NoChanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {

    private VoteRepo voteRepo;

    @Autowired
    public VoteServiceImpl(VoteRepo voteRepo) {
        this.voteRepo = voteRepo;
    }

    @Override
    public Vote create(Vote vote, int userId, int restaurantId) {
        Assert.notNull(vote, "some vote is required");
        Vote oldVote = voteRepo.getByUserIdAndDate(userId, vote.getDate());
        if (oldVote == null) {
            return voteRepo.save(vote, userId, restaurantId);
        }
        if (LocalTime.now().isBefore(LocalTime.of(11, 0))) {
            return voteRepo.save(oldVote, userId, restaurantId);
        } else {
            throw new NoChanceException("No chance to change your mind. Think better next time =)");
        }
    }

    @Override
    public Vote get(int id) {
        return ValidationUtil.checkNotFoundWithId(voteRepo.get(id), id);
    }

    @Override
    public List<Vote> getAll() {
        return voteRepo.getAll();
    }

    @Override
    public List<Vote> getAllByDate(LocalDate date) {
        return voteRepo.getAllByDate(date);
    }

    @Override
    public Vote getByUserIdAndDate(int userId, LocalDate date) {
        return ValidationUtil.checkNotFound(voteRepo.getByUserIdAndDate(userId, date),
                "user id=" + userId + ", date=" + date);
    }

    @Override
    public void delete(int id) {
        ValidationUtil.checkNotFoundWithId(voteRepo.delete(id), id);
    }

    @Override
    public void deleteAll() {
        voteRepo.deleteAll();
    }
}
