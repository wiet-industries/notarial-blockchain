import "./App.css";

import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";

// eslint-disable-next-line import/no-unresolved
import Main from "./Main";
// eslint-disable-next-line import/no-unresolved
import TransactionForm from "./TransactionForm";

const App = () => (
  <div className="App">
    <header className="App-header">
      <Router>
        {/* <NavBar /> */}

        <Routes>
          <Route path="/" element={<Main />} />

          <Route path="/form" element={<TransactionForm />} />

          {/* <Route */}
          {/*  path="/dishdetails/:dishID" */}
          {/*  exact */}
          {/*  component={() => <DishDetails key={window.location.pathname} />} */}
          {/* /> */}
        </Routes>
      </Router>
    </header>
  </div>
);

export default App;
