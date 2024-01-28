import { React } from 'react';
import { Link } from 'react-router-dom';
import './MatchDetailsCard.css';

export const MatchDetailsCard = ({match, teamName}) =>  {
 
    const otherTeam =  teamName === match.homeTeamName ? match.awayTeamName : match.homeTeamName;
    const otherTeamRoute = `/teams/${otherTeam}`;
    const isMatchWon = teamName === match.winnerTeamName;
    return (
    <div className={isMatchWon ? 'MatchDetailsCard won-card' : 'MatchDetailsCard lost-card'}>
       <div>
          <span className='vs'>vs </span>
          <h1> <Link to={otherTeamRoute}> {otherTeam}</Link></h1>
          <h2 className='match-days'>{match.matchDays}</h2>
          <h3 className='match-venue'>at {match.venueName}</h3>
          <h3 className='match-result'>{match.result} </h3>
          <p className='match-highlights'>{match.highlights}</p>
        </div>

        <div className='additional-details'>
          <h3>First Innings</h3>
          <p>{match.homeTeamName} - {match.firstInningScore}</p>
          <h3>Second Innings</h3>
          <p>{match.awayTeamName} - {match.secondInningScore}</p>
          <h3>Toss winning team</h3>
          <p>{match.tossWinningTeam} - {match.decisionToBowlOrBat}</p>
          <h3>Man of the Match</h3>
          <p>{match.playerOfMatch}</p>
          <h3>Points</h3>
          <p>{match.points}</p>
          <h3>Umpires</h3>
          <p>{match.umpire1}, {match.umpire2}</p>
        </div>

    </div>
     
  );
}

