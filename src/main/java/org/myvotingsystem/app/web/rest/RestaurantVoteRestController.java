package org.myvotingsystem.app.web.rest;

import org.myvotingsystem.app.Authorized;
import org.myvotingsystem.app.model.Restaurant;
import org.myvotingsystem.app.model.Vote;
import org.myvotingsystem.app.service.interfaces.RestaurantService;
import org.myvotingsystem.app.service.interfaces.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;

import java.time.LocalDate;
import java.util.List;

import static org.myvotingsystem.app.web.rest.RestaurantVoteRestController.REST_URL;
import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(REST_URL)
public class RestaurantVoteRestController {
    private static final Logger LOG = getLogger(RestaurantVoteRestController.class);
    static final String REST_URL = "/rest/restaurants";
    private RestaurantService restaurantService;
    private VoteService voteService;

    @Autowired
    public RestaurantVoteRestController(RestaurantService restaurantService, VoteService voteService) {
        this.restaurantService = restaurantService;
        this.voteService = voteService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAllRestaurants() {
        LOG.info("getAll restaurants");
        return restaurantService.getAll();
    }

    @PostMapping(value = "/{restaurantId}/votes", produces = MediaType.APPLICATION_JSON_VALUE)
    public Vote create(@PathVariable Integer restaurantId,
                       @AuthenticationPrincipal Authorized authorizedUser) {
        Vote vote = new Vote(null, null, null, LocalDate.now());
        LOG.info("create {}", vote);
        return voteService.create(vote, authorizedUser.getId(), restaurantId);
    }
}
