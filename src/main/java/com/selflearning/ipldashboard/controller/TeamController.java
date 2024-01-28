package com.selflearning.ipldashboard.controller;

import org.springframework.web.bind.annotation.RestController;

import com.selflearning.ipldashboard.model.Match;
import com.selflearning.ipldashboard.model.Team;
import com.selflearning.ipldashboard.repository.MatchRepository;
import com.selflearning.ipldashboard.repository.TeamRepository;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@CrossOrigin
public class TeamController {

    private TeamRepository teamRepository; 
    private MatchRepository matchRepository;

    
    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }
   

    @GetMapping("/team")
    public Iterable<Team> getAllTeam() {
        return this.teamRepository.findAll();
    }
    

    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable String teamName) {
        Team team = this.teamRepository.findByTeamName(teamName);
        team.setMatches(this.matchRepository.findLatestMatchesByTeam(teamName,4));
        return team;
    }

    @GetMapping("/team/{teamName}/matches")
    public List<Match> getMatchesForTeam(@PathVariable String teamName, @RequestParam int year) {
        return this.matchRepository.getMatchesBySeasonForATeam(teamName, year);
    }
    
    
}
