package com.fc.ws.team;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {

    Team findByTeamID(String teamID);
}
