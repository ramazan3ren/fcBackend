package com.fc.ws.squad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fc.ws.match.Match;
import com.fc.ws.match.MatchRepository;

import com.fc.ws.team.TeamRepository;



@Service
public class SquadService {

    @Autowired
    private SquadRepository squadRepository;

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    TeamRepository teamRepository;

    public Squad createSquad(Match match) {

        Squad squad = new Squad(match);
        return squadRepository.save(squad);
    }



}
