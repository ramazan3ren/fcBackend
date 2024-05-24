package com.fc.ws.team;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;  
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fc.ws.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "teams")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, length = 100)
    String teamName;

    @Column(length = 300)
    String teamDesc;

    String teamFounder;

    String teamID;

    @Column(length = 500)
    String teamImage;


    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private  List<User> members = new ArrayList<>();

    public Team() {
    }

    public Team(String teamName, String teamDesc, String teamImage, String teamFounder) {
        this.teamName = teamName;
        this.teamDesc = teamDesc;
        this.teamImage = teamImage;
        this.teamID = UUID.randomUUID().toString();
        this.teamFounder = teamFounder;

    }

    public String getTeamFounder() {
        return teamFounder;
    }

    public void setTeamFounder(String teamFounder) {
        this.teamFounder = teamFounder;
    }

    public void addMember(User user) {
        this.members.add(user);
        user.setTeam(this);
    }

    public void removeMember(User user) {
        this.members.remove(user);
        user.setTeam(null);
    }

    public String getTeamID() {
        return teamID;
    }

    public void setTeamID(String teamID) {
        this.teamID = teamID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamDesc() {
        return teamDesc;
    }

    public void setTeamDesc(String teamDesc) {
        this.teamDesc = teamDesc;
    }

    public String getTeamImage() {
        return teamImage;
    }

    public void setTeamImage(String teamImage) {
        this.teamImage = teamImage;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }
}