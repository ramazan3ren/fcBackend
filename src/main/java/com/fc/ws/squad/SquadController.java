package com.fc.ws.squad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fc.ws.match.Match;
import com.fc.ws.match.MatchService;
import com.fc.ws.team.TeamService;

@RestController
public class SquadController {

    @Autowired
    SquadService squadService;

    @Autowired
    MatchService matchService;

    @Autowired
    TeamService teamService;

    @PostMapping("api/v1/squads")
    public ResponseEntity<Squad> createSquad(@RequestBody String token) {
        Match match = matchService.getMatchByToken(token);

        Squad squad = squadService.createSquad(match);
        return ResponseEntity.status(HttpStatus.CREATED).body(squad);
    }

}
