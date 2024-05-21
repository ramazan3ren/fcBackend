package com.fc.ws.match;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fc.ws.match.dto.MatchDTO;

@RestController

public class MatchController {

    @Autowired
    MatchService matchService;

    @Autowired
    MatchRepository matchRepository;

    @PostMapping("api/v1/matches")
    public ResponseEntity<Match> createMatch(@RequestBody Match matchDTO) {
        Match match = matchService.createMatch(matchDTO.getMatchCreatorUsername(), matchDTO.getCity(),
                matchDTO.getDistrict(), matchDTO.getFacilityName(), matchDTO.getMatchTime());
        return ResponseEntity.status(HttpStatus.CREATED).body(match);
    }

    @GetMapping("api/v1/matches")
    Page<Match> getAllMatches(Pageable page) {

        return matchService.getMatches(page);
    }

    @GetMapping("api/v1/matches/{token}")
    public ResponseEntity<Match> getMatchByToken(@PathVariable String token) {
        Match match = matchService.getMatchByToken(token);
        return ResponseEntity.ok(match);

    }

    @PutMapping("api/v1/matches/{token}")
    public ResponseEntity<Match> updateMatch(@PathVariable String token, @RequestBody MatchDTO matchDTO) {
        Match updatedMatch = matchService.updateMatch(token, matchDTO.getMatchCreatorUsername(), matchDTO.getCity(),
                matchDTO.getDistrict(),
                matchDTO.getFacilityName(), matchDTO.getMatchTime(), matchDTO.getHomeTeam(),
                matchDTO.getAwayTeam());
        return ResponseEntity.ok(updatedMatch);
    }

    @DeleteMapping("api/v1/matches/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable String token) {
        matchService.deleteMatch(token);
        return ResponseEntity.noContent().build();
    }
}