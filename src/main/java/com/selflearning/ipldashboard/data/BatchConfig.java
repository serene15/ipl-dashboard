package com.selflearning.ipldashboard.data;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.selflearning.ipldashboard.model.Match;

@Configuration
public class BatchConfig {
  
    private final String[] FIELD_NAMES = new String[] {
        "season","id","name","short_name","description","home_team","away_team","toss_won","decision","1st_inning_score","2nd_inning_score","winner","result","start_date","end_date","venue_id","venue_name","home_captain","away_captain","pom","points","super_over","home_overs","home_runs","home_wickets","home_boundaries","away_overs","away_runs","away_wickets","away_boundaries","highlights","home_key_batsman","home_key_bowler","home_playx1","away_playx1","away_key_batsman","away_key_bowler","match_days","umpire1","umpire2","tv_umpire","referee","reserve_umpire"
    };

    @Bean
    public FlatFileItemReader<MatchInput> reader() {
        return new FlatFileItemReaderBuilder<MatchInput>()
                .name("MatchItemReader")
                .resource(new ClassPathResource("cricket_data.csv"))
                .delimited()
                .names(FIELD_NAMES)
                .targetType(MatchInput.class)
                .build();
    }

    @Bean
    public MatchDataProcessor processor() {
        return new MatchDataProcessor();
    }
    

    @Bean
    public JdbcBatchItemWriter<Match> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Match>()
                .sql("INSERT INTO match (id,season,full_name_of_both_teams,name_of_both_teams,home_team_name,away_team_name,toss_winning_team,decision_to_bowl_or_bat,first_inning_score,second_inning_score,winner_team_name,result,start_date,end_date,venue_name,player_of_match,highlights,umpire1,umpire2,match_days,points)"
                        +
                        "VALUES (:id,:season,:fullNameOfBothTeams,:nameOfBothTeams,:homeTeamName,:awayTeamName,:tossWinningTeam,:decisionToBowlOrBat,:firstInningScore,:secondInningScore,:winnerTeamName,:result,:startDate,:endDate,:venueName,:playerOfMatch,:highlights,:umpire1,:umpire2,:matchDays,:points)")
                .dataSource(dataSource)
                .beanMapped()
                .build();
    }

    
    @Bean
    public Job importUserJob(JobRepository jobRepository, Step step1, JobCompletionNotificationListener jobCompletionNotificationListener) {
        
        return new JobBuilder("importUserJob", jobRepository)
                .listener(jobCompletionNotificationListener)
                .start(step1)
                .build();
       
    }


    @Bean
    public Step step1(JobRepository jobRepository,PlatformTransactionManager transactionManager,
            FlatFileItemReader<MatchInput> reader, MatchDataProcessor processor, JdbcBatchItemWriter<Match> writer) {
        return new StepBuilder("step1", jobRepository)
                .<MatchInput, Match>chunk(10, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    
    }
}
