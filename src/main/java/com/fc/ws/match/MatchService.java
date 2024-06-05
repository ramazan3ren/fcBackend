package com.fc.ws.match;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fc.ws.team.Team;
import com.fc.ws.team.TeamRepository;

@Service
public class MatchService {

    @Autowired
    MatchRepository matchRepository;
    @Autowired
    TeamRepository teamRepository;

    @Transactional
    public Match createMatch(String matchCreatorUsername, String city, String district,
            String facilityName, String matchTime, Long homeTeamId, Long awayTeamId) {

        Team home = teamRepository.findById(homeTeamId).orElseThrow(RuntimeException::new);
        Team away = teamRepository.findById(awayTeamId).orElseThrow(RuntimeException::new);
        Match match = new Match(matchCreatorUsername, city, district, facilityName, matchTime, home, away);
        match.setToken(match.generateToken()); 
        return matchRepository.save(match);
    }

    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }


    @Transactional
    public Match updateMatch(String token, String matchCreatorUsername, String city, String district,
            String facilityName, String matchTime, Team homeTeam,
            Team awayTeam) {
        Match match = matchRepository.findMatchByToken(token);
        match.setAwayTeam(awayTeam);
        match.setHomeTeam(homeTeam);
        match.setCity(city);
        match.setDistrict(district);
        match.setFacilityName(facilityName);
        match.setMatchTime(matchTime);
        match.setMatchCreatorUsername(matchCreatorUsername);
        return matchRepository.save(match);
    }


    @Transactional
    public void deleteMatch(String token) {
        Match match = matchRepository.findMatchByToken(token);
        matchRepository.delete(match);
    }

    public Match getMatchById(Long id) {
        Match match = matchRepository.findById(id).orElseThrow(RuntimeException::new);
        return match;
    }

    public Page<Match> getMatches(Pageable page) {
        return matchRepository.findAll(page);
    }

}