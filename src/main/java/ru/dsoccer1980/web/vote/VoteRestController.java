package ru.dsoccer1980.web.vote;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dsoccer1980.web.AuthorizedUser;
import ru.dsoccer1980.model.Vote;
import ru.dsoccer1980.service.VoteService;
import ru.dsoccer1980.util.exception.VoteException;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController {

    static final String REST_URL = "/rest/votes";

    @Autowired
    private VoteService voteService;

    @PostMapping(value = "/restaurant/{restaurantId}/date/{date}")
    public ResponseEntity<Void> createWithLocation(
            @PathVariable("restaurantId") int restaurantId,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        Vote created = voteService.save(AuthorizedUser.getId(), restaurantId, date);
        if (created == null) throw new VoteException("You can not vote today more");
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/user/")
    public List<Vote> getAllVotesByUser() {
        return voteService.getAllVotesByUser(AuthorizedUser.getId());
    }

    @DeleteMapping(value = "/{date}")
    public void delete(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        voteService.delete(AuthorizedUser.getId(), date);
    }

    @GetMapping(value = "/{date}")
    public Vote get(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return voteService.get(AuthorizedUser.getId(), date);
    }

}