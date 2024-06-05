package com.fc.ws.team;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fc.ws.user.UserRepository;
import com.fc.ws.user.User;


@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private UserRepository userRepository;


    public Team createTeam(String teamName, String teamImage, String userEmail) {

        Team team = new Team(teamName, teamImage);
        teamRepository.save(team);

        User user = userRepository.findByEmail(userEmail);
        
        user.setTeam(team);
        userRepository.save(user);


        return team;
        
    }

    public Optional<Team> getTeam(Long id) {
        Optional<Team> team = teamRepository.findById(id);
        return team;
    }

    public Page<Team> getTeams(Pageable page) {
        return teamRepository.findAll(page);
    }

    public boolean joinTeam(Long id, String userEmail) {
        Team team = teamRepository.findById(id).orElseThrow(RuntimeException::new);

        
        User user = userRepository.findByEmail(userEmail);
        
        user.setTeam(team);
        userRepository.save(user);
        return true;
    }

    
}
