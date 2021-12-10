import './App.css';
import Button from '@mui/material/Button';

const  App = ({}) => {

  return (
    <div className="App">
      <header className="App-header">
        <p>
          notarial-blockchain
        </p>
        <Button variant="contained">Connect to server</Button>
        <Button variant="contained">Send broadcast to server</Button>
        <Button variant="contained">Disconnect from server</Button>

      </header>
    </div>
  );
}


export default App;
