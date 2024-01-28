import { React } from 'react';
import { Link } from 'react-router-dom';

import './MatchSmallCard.css';

export const MatchSmallCard = ({match, teamName}) =>  {
    if(!match) return null;
    const otherTeam =  teamName === match.homeTeamName ? match.awayTeamName : match.homeTeamName;
    const otherTeamRoute = `/teams/${otherTeam}`;
    const isMatchWon = teamName === match.winnerTeamName;
    return (
    <div className={isMatchWon ? 'MatchSmallCard won-card' : 'MatchSmallCard lost-card'}>
        <span className='vs'>vs </span>
        <h1><Link to={otherTeamRoute}> {otherTeam}</Link></h1>
        <p className='match-result'>{match.result}</p>
    </div>
  );
}

