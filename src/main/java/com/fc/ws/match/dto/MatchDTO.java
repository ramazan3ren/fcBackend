package com.fc.ws.match.dto;

import com.fc.ws.match.Match;

public class MatchDTO {
    String matchCreatorUsername;
    String city;
    String district;
    String facilityName;
    String matchTime;
    Long homeTeamId;
    Long awayTeamId;

    public MatchDTO() {
        
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

    public Long getHomeTeamId() {
        return homeTeamId;
    }


    public Long getAwayTeamId() {
        return awayTeamId;
    }
}