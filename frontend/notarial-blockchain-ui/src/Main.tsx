import "./App.css";

import { CircularProgress } from "@material-ui/core";
import Button from "@mui/material/Button";
import axios from "axios";
import React, { useCallback, useState } from "react";
import { Link } from "react-router-dom";

type ConnectionType = "NOT_CONNECTED" | "CONNECTING_ERROR" | "CONNECTED";
type User = {
  ID?: string;
};

const BASE_URL = "http://localhost:8080";
const NODE = "/node";
const CONNECT = "/connect";
const BROADCAST = "/broadcast";
const DISCONNECT = "/disconnect";
const REGISTER = "/register";

const Main = () => {
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

  const register = () => {
    axios
      .post(BASE_URL + NODE + REGISTER)
      .then((response) => {
        if (response.status === 200 && response.data.message === "OK") {
          setMessage((prevState) => ["Registered", ...prevState.slice(0, 6)]);
          setUserData({ ID: response.data.ID });
        } else {
          setMessage((prevState) => [
            "Error while registering",
            ...prevState.slice(0, 6),
          ]);
        }
      })
      .catch(() =>
        setMessage((prevState) => [
          "Error while registering",
          ...prevState.slice(0, 6),
        ])
      )
      .finally(() =>
        setMessage((prevState) => ["End of registry", ...prevState.slice(0, 6)])
      );
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
    <>
      {!loading && <p>{connectionMessage}</p>}
      {loading && <CircularProgress />}
      <p>notarial-blockchain</p>
      <div className="my-5">
        <p>
          ID:&nbsp;
          {user.ID}
        </p>
      </div>

      <div className="d-flex justify-content-around w-50">
        <Button variant="contained" onClick={connect}>
          Connect to server
        </Button>
        <Button variant="contained" onClick={register}>
          Register to server
        </Button>
        <Button variant="contained" onClick={broadcast}>
          Send broadcast to server
        </Button>
        <Button variant="contained" onClick={disconnect}>
          Disconnect from server
        </Button>
        <Link to="/form">
          <Button variant="contained">Add transaction</Button>
        </Link>
      </div>

      <div className="my-5">
        <ul>
          {message.map((item, id) => (
            <li key={`item-${id}`}>{item}</li>
          ))}
        </ul>
      </div>
    </>
  );
};

export default Main;
