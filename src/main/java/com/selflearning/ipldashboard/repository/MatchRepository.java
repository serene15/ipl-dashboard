package com.selflearning.ipldashboard.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.selflearning.ipldashboard.model.Match;

public interface MatchRepository extends CrudRepository<Match,Long>{


    List<Match> getByHomeTeamNameOrAwayTeamNameOrderByStartDateDesc(String homeTeamName,String awayTeamName, Pageable pageSize);

    default List<Match> findLatestMatchesByTeam(String teamName, int count){
        return getByHomeTeamNameOrAwayTeamNameOrderByStartDateDesc(teamName, teamName, PageRequest.of(0, count));
    }

    // used a Named Query to avoid complex method names
    @Query("select m from Match m where (m.homeTeamName = :teamName or m.awayTeamName = :teamName) and m.season = :year Order By m.startDate Desc")
    List<Match> getMatchesBySeasonForATeam(@Param("teamName") String teamName, @Param("year") int year);
    //List<Match> findBySeasonAndHomeTeamNameOrSeasonAndAwayTeamName(int year, String homeTeamName, int season, String awayTeamName);
}
