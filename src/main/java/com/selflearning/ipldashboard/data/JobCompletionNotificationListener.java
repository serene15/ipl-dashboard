package com.selflearning.ipldashboard.data;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.selflearning.ipldashboard.model.Team;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Component
public class JobCompletionNotificationListener implements JobExecutionListener {

  private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

  // private final JdbcTemplate jdbcTemplate;

  // public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
  //   this.jdbcTemplate = jdbcTemplate;
  // }

  private final EntityManager entityManager;

  public JobCompletionNotificationListener(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public void beforeJob(JobExecution jobExecution) {
      System.out.println(" Before Job started executing:-- ");
  }

  @Override
  @Transactional
  public void afterJob(JobExecution jobExecution) {
    if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
      log.info("!!! JOB FINISHED! Time to verify the results");

      Query query = entityManager.createQuery("SELECT m.nameOfBothTeams from Match m ");
      List<String> bothTeamNames = query.getResultList();
     
      Supplier<Stream<String>> allTeams = () -> bothTeamNames.stream().flatMap(e -> Arrays.asList(e.split(" v ")).stream());
      // finding teams
      Supplier<Stream<String>> teams =  () -> allTeams.get().distinct();
      List<String> teamNames= teams.get().toList();

      // finding total matches played by each team
      Supplier<Stream<Long>> totalMatchesPlayed = () ->  teams.get().map(e -> allTeams.get().filter(a -> a.equals(e)).count());
     
      List<Long> totalMatches =  totalMatchesPlayed.get().toList();

      // find total wins of each team
      Supplier<Stream<String>> winnerTeams = () -> entityManager.createQuery("SELECT m.winnerTeamName from Match m").getResultList().stream();
      Supplier<Stream<Long>> totalWins = () ->  teams.get().map(t -> winnerTeams.get().filter(w -> w.equals(t)).count());
      List<Long> wins = totalWins.get().toList();

      for (int j = 0; j < teams.get().count(); j++) {
        Team t= new Team(teamNames.get(j));
        t.setTotalMatches(totalMatches.get(j));
        t.setTotalWins(wins.get(j));
        entityManager.persist(t);
      }

    }
    else if(jobExecution.getStatus() == BatchStatus.FAILED){
      System.out.println("Execution failed...........................................................................................");
    }
    else if(jobExecution.getStatus() == BatchStatus.ABANDONED){
      System.out.println("Execution abandoned...........................................................................................");
    }
    else if(jobExecution.getStatus() == BatchStatus.STARTED){
      System.out.println("Execution started...........................................................................................");
    }
    else if(jobExecution.getStatus() == BatchStatus.STARTING){
      System.out.println("Execution starting...........................................................................................");
    }
    else if(jobExecution.getStatus() == BatchStatus.STOPPING){
      System.out.println("Execution stopping...........................................................................................");
    }
    else if(jobExecution.getStatus() == BatchStatus.STOPPED){
      System.out.println("Execution stopped...........................................................................................");
    }
    else if(jobExecution.getStatus() == BatchStatus.UNKNOWN){
      System.out.println("Execution unknown...........................................................................................");
    }

  }
}
