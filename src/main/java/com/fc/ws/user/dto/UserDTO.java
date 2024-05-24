package com.fc.ws.user.dto;

import com.fc.ws.squad.Squad;
import com.fc.ws.team.Team;
import com.fc.ws.user.User;

public class UserDTO {
    long id;

    String username;

    String email;

    String image;

    String firstname;

    String lastname;


    private Team team;

    Squad squad;

    public UserDTO(User user) {
        setId(user.getId());
        setUsername(user.getUsername());
        setEmail(user.getEmail());
        setImage(user.getImage());
        setFirstname(user.getFirstName());
        setLastname(user.getLastName());
        setTeam(user.getTeam());
        setSquad(user.getSquad());

    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Squad getSquad() {
        return squad;
    }

    public void setSquad(Squad squad) {
        this.squad = squad;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
