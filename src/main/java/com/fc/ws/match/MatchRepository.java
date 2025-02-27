package com.fc.ws.match;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {

Match findMatchByToken(String token);

}