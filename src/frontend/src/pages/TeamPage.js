import { React, useEffect, useState } from 'react';
import { MatchDetailsCard } from '../components/MatchDetailsCard';
import { MatchSmallCard } from '../components/MatchSmallCard';
import { useParams } from 'react-router-dom';
import { PieChart } from 'react-minimal-pie-chart';
import { Link } from 'react-router-dom';

import './TeamPage.css';

export const TeamPage = () =>  {

    const [team, setTeam] = useState({matches: []});
    const { teamName } = useParams();    

    useEffect(
        () => {
            const fetchMatches = async () => {
                const response = await fetch(`${process.env.REACT_APP_ROOT_API_URL}/team/${teamName}`);
                const data = await response.json();
                setTeam(data);
            };
            fetchMatches();
        },
        [teamName]
    );
    
    if(!team || !team.teamName){
        return <h1>Team Not Found</h1>
    }

  return (
    <div className="TeamPage">
        <div className='team-name-section'>
          <p className='home-link'> <Link to={`/`}>&lt; &lt; Home </Link> </p>        
          <h1 className='team-name'>{team.teamName}</h1>
        </div>
        <div className='win-loss-section'> Wins/Losses
        <PieChart
               data={[
                { title: 'Losses', value: team.totalMatches - team.totalWins, color: '#a34d5d' },
                 { title: 'Wins', value: team.totalWins, color: '#4da375' }
               ]}
             />
        </div>
        <div className='match-detail-section'>
        <h3 className='latest-matches-heading'>Latest Matches</h3>
        <MatchDetailsCard  teamName={team.teamName} match={team.matches[0]}></MatchDetailsCard>
        </div>
        {team.matches.slice(1).map(match => <div key={match.id} className='match-small-card-section'><MatchSmallCard  teamName={team.teamName} match ={match}></MatchSmallCard></div>)}
        <div className='more-link'>
          <Link to={`/teams/${teamName}/matches/${process.env.REACT_APP_DATA_END_YEAR}`}>
          <p>More &gt; &gt; </p>
          </Link>
        </div>
    </div>
  );
}


