package com.fc.ws.match.dto;

import com.fc.ws.match.Match;
import com.fc.ws.team.Team;

public class MatchDTO {
    String matchCreatorUsername;
    String city;
    String district;
    String facilityName;
    String matchTime;
    Team homeTeam;
    Team awayTeam;

    public MatchDTO(Match match) {
        setMatchCreatorUsername(match.getMatchCreatorUsername());
        setCity(match.getCity());
        setDistrict(match.getDistrict());
        setFacilityName(match.getFacilityName());
        setMatchTime(match.getMatchTime());
        setHomeTeam(match.getHomeTeam());
        setAwayTeam(match.getAwayTeam());
    }

    public String getMatchCreatorUsername() {
        return matchCreatorUsername;
    }

    public void setMatchCreatorUsername(String matchCreatorUsername) {
        this.matchCreatorUsername = matchCreatorUsername;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(String matchTime) {
        this.matchTime = matchTime;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }
}