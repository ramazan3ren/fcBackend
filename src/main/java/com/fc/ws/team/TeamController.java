package com.fc.ws.team;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fc.ws.team.dto.TeamDTO;

@RestController
public class TeamController {

    @Autowired
    TeamService teamService;

    @PostMapping("api/v1/teams")
    public ResponseEntity<Team> createTeam(@RequestBody TeamDTO teamDTO, @RequestHeader("email") String userEmail) {
        Team team = teamService.createTeam(teamDTO.getTeamName(), teamDTO.getTeamImage(), userEmail);
        return ResponseEntity.status(HttpStatus.CREATED).body(team);

    }

    @GetMapping("api/v1/teams")
    Page<Team> getTeams(Pageable page) {
        return teamService.getTeams(page);

    }

    @PostMapping("api/v1/teams/{id}/join")
    boolean joinTeam(@PathVariable Long id, @RequestHeader("email") String userEmail) {
        return teamService.joinTeam(id,userEmail);

    }

    @GetMapping("api/v1/teams/{id}")
    public ResponseEntity<Optional<Team>> getTeamByTeamID(@PathVariable Long id, TeamDTO teamDTO) {
        Optional<Team> team = teamService.getTeam(id);
        return ResponseEntity.ok().body(team);

    }
}
