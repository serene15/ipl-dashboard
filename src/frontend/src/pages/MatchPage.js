import { React, useEffect, useState } from 'react';
import { MatchDetailsCard } from '../components/MatchDetailsCard';
import { useParams } from 'react-router-dom';
import { Link } from 'react-router-dom';

import './MatchPage.css';
import { YearSelector } from '../components/YearSelector';

export const MatchPage = () =>  {

  const [matches, setMatches] = useState([]);
  const {teamName, year} = useParams();

  useEffect(
    () => {
        const fetchMatches = async () => {
            const response = await fetch(`${process.env.REACT_APP_ROOT_API_URL}/team/${teamName}/matches?year=${year}`);
            const data = await response.json();
            setMatches(data);
        };
        fetchMatches();
    },
    [teamName, year]
);

  return (
    <div className="MatchPage">
       
       <div className='year-selector'>
       <p className='home-link'> <Link to={`/`}>&lt; &lt; Home </Link> </p>        
        <h3> Select Year </h3>
       <YearSelector key={year} teamName={teamName}></YearSelector>
       </div>
       
        <div>
        <h1 className='page-heading'>{teamName} matches in {year}</h1>
        
        {
          matches.length 
          ?  matches.map(match => <MatchDetailsCard key={match.id} teamName={teamName} match ={match}></MatchDetailsCard>)
          :  <h2 className='not-play-heading'>Seems like {teamName} did not play in {year} !!</h2>
        }
        </div>
    </div>
  );
}


