
package com.selflearning.ipldashboard.data;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import com.selflearning.ipldashboard.model.Match;


public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

  //private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

  @Override
  public Match process(final MatchInput matchInput) {
    
    Match match = new Match();

    match.setId(Long.parseLong(matchInput.getId()));
    match.setVenueName(matchInput.getVenue_name());
    match.setStartDate(matchInput.getStart_date());
    match.setEndDate(matchInput.getEnd_date());
    match.setPlayerOfMatch(matchInput.getPom());
    
    // find firstInningsteam and secondInningsTeam based on toss decision
    String firstInningsTeam, secondInningTeam;

    if(matchInput.getDecision().equals("BAT FIRST")){
      firstInningsTeam = matchInput.getToss_won();
      secondInningTeam =  matchInput.getToss_won().equals(matchInput.getHome_team()) ? matchInput.getAway_team() : matchInput.getHome_team();
    }
    else{
      secondInningTeam = matchInput.getToss_won();
      firstInningsTeam = matchInput.getToss_won().equals(matchInput.getHome_team())? matchInput.getAway_team() : matchInput.getHome_team();
    }

    match.setHomeTeamName(firstInningsTeam);
    match.setAwayTeamName(secondInningTeam);
    match.setFirstInningScore(matchInput.getFirst_inning_score());
    match.setSecondInningScore(matchInput.getSecond_inning_score());


    match.setTossWinningTeam(matchInput.getToss_won());
    match.setDecisionToBowlOrBat(matchInput.getDecision());
    match.setResult(matchInput.getResult());
    match.setHighlights(matchInput.getHighlights());
    match.setFullNameOfBothTeams(matchInput.getName());
    match.setNameOfBothTeams(matchInput.getShort_name());
    match.setWinnerTeamName(matchInput.getWinner());
    match.setSeason(Integer.parseInt(matchInput.getSeason()));
    match.setUmpire1(matchInput.getUmpire1());
    match.setUmpire2(matchInput.getUmpire2());
    match.setMatchDays(matchInput.getMatch_days());
    match.setPoints(matchInput.getPoints());
    
    return match;

  }

}

