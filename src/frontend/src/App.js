import {HashRouter as Router, Routes, Route} from 'react-router-dom'
import './App.css';
import { TeamPage } from './pages/TeamPage';
import { MatchPage } from './pages/MatchPage';
import { HomePage } from './pages/HomePage';

function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route path="/teams/:teamName" element={<TeamPage></TeamPage>}>
          </Route>
          <Route path="/teams/:teamName/matches/:year" element={<MatchPage></MatchPage>}>
          </Route>
          <Route path="/" element={<HomePage></HomePage>}>
          </Route>
        </Routes>
      </Router>
    </div>
  );
}

export default App;
