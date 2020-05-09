package org.myvotingsystem.app.web.rest;

import org.myvotingsystem.app.model.Vote;
import org.myvotingsystem.app.service.interfaces.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;

import java.time.LocalDate;
import java.util.List;

import static org.myvotingsystem.app.web.rest.VoteRestController.REST_URL;
import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(REST_URL)
public class VoteRestController {
    private static final Logger LOG = getLogger(VoteRestController.class);
    static final String REST_URL = "/rest/admin/votes";
    private VoteService voteService;

    @Autowired
    public VoteRestController(VoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vote> getAll(@RequestParam(value = "date")
                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        LOG.info("getAll votes date {}", date);
        return voteService.getAllByDate(date);
    }
}
