package com.fc.ws.team;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fc.ws.user.User;
import com.fc.ws.user.UserRepository;

@Service
public class TeamService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;


    public Team createTeam(String teamName, String teamDesc, String teamImage, List<String> usernames,
            String teamFounder) {

        Team team = new Team(teamName, teamDesc, teamImage, teamFounder);
        User userFounder = userRepository.findByUsername(teamFounder);
        if (userFounder != null) {
            team.addMember(userFounder);
        }
        for (String username : usernames) {
            User user = userRepository.findByUsername(username);
            if (user != null) {
                team.addMember(user);
            } else {

            }
        }

        return teamRepository.save(team);
    }

    public Team updateTeamMembers(String teamID, String username) throws Exception {

        Team team = teamRepository.findByTeamID(teamID);
        if (team == null) {
            throw new Exception("Takım bulunamadı.");
        }

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new Exception("Kullanıcı bulunamadı.");
        }

        team.addMember(user);

        return teamRepository.save(team);
    }

    public Team removeTeamMembers(String teamID, String username) throws Exception {

        Team team = teamRepository.findByTeamID(teamID);
        if (team == null) {
            throw new Exception("Takım bulunamadı.");
        }

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new Exception("Kullanıcı bulunamadı.");
        }

        team.removeMember(user);

        return teamRepository.save(team);
    }

    public Team getTeam(String teamID) {
        Team team = teamRepository.findByTeamID(teamID);
        return team;
    }

    public Page<Team> getTeams(Pageable page) {
        return teamRepository.findAll(page);
    }

    
}
