package com.selflearning.ipldashboard.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

import java.util.List;

import com.selflearning.ipldashboard.model.Match;

@Entity
public class Team {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String teamName;
    private long totalMatches;
    private long totalWins;

    @Transient
    private List<Match> matches;

    public Team() {
    }
 
    public Team(String teamName){
        this.teamName = teamName;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTeamName() {
        return teamName;
    }
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public long getTotalMatches() {
        return totalMatches;
    }
    public void setTotalMatches(long totalMatches) {
        this.totalMatches = totalMatches;
    }
    public long getTotalWins() {
        return totalWins;
    }
    public void setTotalWins(long totalWins) {
        this.totalWins = totalWins;
    }
    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
    @Override
    public String toString() {
        return "Team " + this.teamName + " played total " + this.totalMatches + " matches and won " + this.totalWins + " matches out of them." ;
    }
    
}
