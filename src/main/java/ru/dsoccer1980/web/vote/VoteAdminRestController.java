package ru.dsoccer1980.web.vote;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.dsoccer1980.model.Restaurant;

import ru.dsoccer1980.service.VoteService;

import java.time.LocalDate;
import java.util.Map;


@RestController
@RequestMapping(value = VoteAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteAdminRestController {

    static final String REST_URL = "/rest/admin/votes";

    @Autowired
    private VoteService voteService;

    @GetMapping(value = "/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<Restaurant, Long> getVotesAmountForRestaurantsByDate(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return voteService.getVotesAmountForRestaurantsByDate(date);
    }


}