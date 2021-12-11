import "./App.css";

import { CircularProgress } from "@material-ui/core";
import Button from "@mui/material/Button";
import axios from "axios";
import React, { useCallback, useState } from "react";

type ConnectionType = "NOT_CONNECTED" | "CONNECTING_ERROR" | "CONNECTED";
type User = {
  port?: string;
  ip?: string;
};

const BASE_URL = "http://localhost:8080";
const NODE = "/node";
const CONNECT = "/connect";
const BROADCAST = "/broadcast";
const DISCONNECT = "/disconnect";

const App = () => {
  const [connected, setConnected] = useState<ConnectionType>("NOT_CONNECTED");
  const [loading, setLoading] = useState(false);
  const [user, setUserData] = useState<User>({});
  const [message, setMessage] = useState<string[]>([]);

  const connect = () => {
    setLoading(true);
    setMessage((prevState) => ["Connecting", ...prevState.slice(0, 6)]);
    axios
      .post(BASE_URL + NODE + CONNECT)
      .then((response) => {
        if (response.status === 200 && response.data.message === "OK") {
          setMessage((prevState) => [
            "Connected successfully",
            ...prevState.slice(0, 6),
          ]);
          setConnected("CONNECTED");
          setUserData({ port: response.data.port, ip: response.data.ip });
        } else {
          setMessage((prevState) => [
            "Error while connecting",
            ...prevState.slice(0, 6),
          ]);
          setConnected("CONNECTING_ERROR");
        }
      })
      .catch(() => {
        setMessage((prevState) => [
          "Error while connecting",
          ...prevState.slice(0, 6),
        ]);
        setConnected("CONNECTING_ERROR");
      })
      .finally(() => setLoading(false));
  };

  const broadcast = () => {
    axios
      .post(BASE_URL + NODE + BROADCAST)
      .then((response) => {
        if (response.status === 200 && response.data.message === "OK") {
          setMessage((prevState) => ["Broadcasting", ...prevState.slice(0, 6)]);
        } else {
          setMessage((prevState) => [
            "Error while broadcasting",

            ...prevState.slice(0, 6),
          ]);
        }
      })
      .catch(() =>
        setMessage((prevState) => [
          "Error while broadcasting",
          ...prevState.slice(0, 6),
        ])
      )
      .finally(() =>
        setMessage((prevState) => [
          "End of broadcasting",
          ...prevState.slice(0, 6),
        ])
      );
  };
  const disconnect = () => {
    setMessage((prevState) => ["Disconnecting", ...prevState.slice(0, 6)]);
    axios
      .delete(BASE_URL + NODE + DISCONNECT)
      .then((response) => {
        if (response.status === 200 && response.data.message === "OK") {
          setMessage((prevState) => [
            "Disconnected from server",
            ...prevState.slice(0, 6),
          ]);
        }
      })
      .catch(() =>
        setMessage((prevState) => [
          "Error while disconnecting",
          ...prevState.slice(0, 6),
        ])
      );
  };

  const connectionMessage = useCallback(() => {
    switch (connected) {
      case "CONNECTED":
        return "Connected";
      case "NOT_CONNECTED":
        return "Not connected!";
      case "CONNECTING_ERROR":
        return "Error while connecting";
      default:
        return "";
    }
  }, [connected]);

  return (
    <div className="App">
      <header className="App-header">
        {!loading && <p>{connectionMessage}</p>}
        {loading && <CircularProgress />}
        <p>notarial-blockchain</p>
        <div className="my-5">
          <p>
            Port:
            {user.port}
          </p>
          <p>
            IP:
            {user.ip}
          </p>
        </div>

        <div className="d-flex justify-content-around w-50">
          <Button variant="contained" onClick={connect}>
            Connect to server
          </Button>
          <Button variant="contained" onClick={broadcast}>
            Send broadcast to server
          </Button>
          <Button variant="contained" onClick={disconnect}>
            Disconnect from server
          </Button>
        </div>

        <div className="my-5">
          <ul>
            {message.map((item, id) => (
              <li key={`item-${id}`}>{item}</li>
            ))}
          </ul>
        </div>
      </header>
    </div>
  );
};

export default App;
