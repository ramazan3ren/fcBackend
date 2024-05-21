package com.fc.ws.team;

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
import org.springframework.web.bind.annotation.RestController;

import com.fc.ws.team.dto.TeamDTO;
import com.fc.ws.user.User;

@RestController
public class TeamController {

    @Autowired
    TeamService teamService;

    @PostMapping("api/v1/teams")
    public ResponseEntity<Team> createTeam(@RequestBody TeamDTO teamDTO) {
        Team team = teamService.createTeam(teamDTO.getTeamName(), teamDTO.getTeamDesc(), teamDTO.getTeamImage(),
                teamDTO.getMembers(), teamDTO.getTeamFounder());
        return ResponseEntity.status(HttpStatus.CREATED).body(team);

    }

    @GetMapping("api/v1/teams")
    Page<Team> getTeams(Pageable page) {
        return teamService.getTeams(page);

    }

    @GetMapping("api/v1/teams/{teamID}")
    public ResponseEntity<Team> getTeamByTeamID(@PathVariable String teamID, TeamDTO teamDTO) {
        Team team = teamService.getTeam(teamID);
        return ResponseEntity.ok().body(team);

    }

    @PutMapping(path = "api/v1/teams/{teamID}/jointeam")
    public ResponseEntity<Team> addUserToTeam(@PathVariable String teamID, @RequestBody(required = true) User username)
            throws Exception {
        Team team = teamService.updateTeamMembers(teamID, username.getUsername());
        return ResponseEntity.ok().body(team);

    }

    @PutMapping(path = "api/v1/teams/{teamID}/leaveteam")
    public ResponseEntity<Team> removeUserToTeam(@PathVariable String teamID,
            @RequestBody(required = true) User username)
            throws Exception {
        Team team = teamService.removeTeamMembers(teamID, username.getUsername());
        return ResponseEntity.ok().body(team);

    }

}
