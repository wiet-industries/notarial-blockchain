import './App.css';
import Button from '@mui/material/Button';

const  App = () => {

  return (
    <div className="App">
      <header className="App-header">
        <p>
          notarial-blockchain
        </p>
        <div className="d-flex justify-content-around w-50">
          <Button variant="contained">Connect to server</Button>
          <Button variant="contained">Send broadcast to server</Button>
          <Button variant="contained">Disconnect from server</Button>
        </div>

        <div className="my-5">
          <p>TU JEST MESSAGE</p>
        </div>

      </header>
    </div>
  );
}


export default App;
