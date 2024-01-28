package com.selflearning.ipldashboard.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.Id;



@Entity
public class Match {
    
    @Id
    private long id;
    private int season;
    private String fullNameOfBothTeams;
    private String nameOfBothTeams;
    private String homeTeamName;
    private String awayTeamName;
    private String tossWinningTeam;
    private String decisionToBowlOrBat;
    private String firstInningScore;
    private String secondInningScore;
    private String winnerTeamName;
    private String result;
    private String startDate;
    private String endDate;
    private String venueName;
    private String playerOfMatch;
    @Column(length = 3000)
    private String highlights;
    private String umpire1;
    private String umpire2;
    private String points;
    //private long venueId;
       // private String homeCaptain;
    //private String awayCaptain;
     //private boolean superOver;
    // private String home_overs;
    // private String home_runs;
    // private String home_wickets;
    // private String home_boundaries;
    // private String away_overs;
    // private String away_runs;
    // private String away_wickets;
    // private String away_boundaries;
    // private String tv_umpire;
    // private String referee;
    // private String reserve_umpire;
     //private String description;
     // private String home_key_batsman;
    // private String home_key_bowler;
    // private String home_playx1;
    // private String away_playx1;
    // private String away_key_batsman;
    // private String away_key_bowler;
    private String matchDays;
    
    public int getSeason() {
        return season;
    }
    public void setSeason(int season) {
        this.season = season;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getFullNameOfBothTeams() {
        return fullNameOfBothTeams;
    }
    public void setFullNameOfBothTeams(String fullNameOfBothTeams) {
        this.fullNameOfBothTeams = fullNameOfBothTeams;
    }
    public String getNameOfBothTeams() {
        return nameOfBothTeams;
    }
    public void setNameOfBothTeams(String nameOfBothTeams) {
        this.nameOfBothTeams = nameOfBothTeams;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }
    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }
    public String getAwayTeamName() {
        return awayTeamName;
    }
    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }
    public String getTossWinningTeam() {
        return tossWinningTeam;
    }
    public void setTossWinningTeam(String tossWinningTeam) {
        this.tossWinningTeam = tossWinningTeam;
    }
    public String getDecisionToBowlOrBat() {
        return decisionToBowlOrBat;
    }
    public void setDecisionToBowlOrBat(String decisionToBowlOrBat) {
        this.decisionToBowlOrBat = decisionToBowlOrBat;
    }
    public String getFirstInningScore() {
        return firstInningScore;
    }
    public void setFirstInningScore(String firstInningScore) {
        this.firstInningScore = firstInningScore;
    }
    public String getSecondInningScore() {
        return secondInningScore;
    }
    public void setSecondInningScore(String secondInningScore) {
        this.secondInningScore = secondInningScore;
    }
    public String getWinnerTeamName() {
        return winnerTeamName;
    }
    public void setWinnerTeamName(String winnerTeamName) {
        this.winnerTeamName = winnerTeamName;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
   
    public String getVenueName() {
        return venueName;
    }
    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }
    
    public String getPlayerOfMatch() {
        return playerOfMatch;
    }
    public void setPlayerOfMatch(String playerOfMatch) {
        this.playerOfMatch = playerOfMatch;
    }
   
    public String getHighlights() {
        return highlights;
    }
    public void setHighlights(String highlights) {
        this.highlights = highlights;
    }
   
    public String getUmpire1() {
        return umpire1;
    }
    public void setUmpire1(String umpire1) {
        this.umpire1 = umpire1;
    }
    public String getUmpire2() {
        return umpire2;
    }
    public void setUmpire2(String umpire2) {
        this.umpire2 = umpire2;
    }
    public String getMatchDays() {
        return matchDays;
    }
    public void setMatchDays(String matchDays) {
        this.matchDays = matchDays;
    }
    public String getPoints() {
        return points;
    }
    public void setPoints(String points) {
        this.points = points;
    }
    

}
