package com.fc.ws.match;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fc.ws.team.Team;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "matches")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String token;

    String matchCreatorUsername;

    String city;

    String district;

    String facilityName;

    String matchTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "home_team_id")
    Team homeTeam;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "away_team_id")
    Team awayTeam;

    public Match() {
    }

    public Match(String matchCreatorUsername, String city, String district, String facilityName, String matchTime, Team homeTeam, Team awayTeam) {
        this.matchCreatorUsername = matchCreatorUsername;
        this.city = city;
        this.district = district;
        this.facilityName = facilityName;
        this.matchTime = matchTime;
        this.setHomeTeam(homeTeam);
        this.setAwayTeam(awayTeam);
    }

    public String generateToken() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setHomeTeam(Team list) {
        this.homeTeam = list;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team team) {
        this.awayTeam = team;
    }
}