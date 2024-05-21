package com.fc.ws.team.dto;

import java.util.List;

public class TeamDTO {

    String teamName;

    String teamID;

    String teamImage;

    String teamDesc;

    String teamFounder;

    List<String> members;

    public String getTeamFounder() {
        return teamFounder;
    }

    public void setTeamFounder(String teamFounder) {
        this.teamFounder = teamFounder;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamID() {
        return teamID;
    }

    public void setTeamID(String teamID) {
        this.teamID = teamID;
    }

    public String getTeamImage() {
        return teamImage;
    }

    public void setTeamImage(String teamImage) {
        this.teamImage = teamImage;
    }

    public String getTeamDesc() {
        return teamDesc;
    }

    public void setTeamDesc(String teamDesc) {
        this.teamDesc = teamDesc;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }
}
