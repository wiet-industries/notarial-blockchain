import { Button, FormControl, TextField } from "@mui/material";
import axios from "axios";
import React, { useState } from "react";

type Answer = {
  answer?: string;
  result?: number;
};

const DividendsPayment = () => {
  const [message, setMessage] = useState<string | undefined>();
  const [author, setAuthor] = useState<undefined | string>(undefined);
  const [companyId, setCompanyId] = useState<undefined | number>(undefined);
  const [priority, setPriority] = useState<undefined | number>(undefined);
  const [answer, setVoting] = useState<(undefined | Answer)[]>([]);
  const [question, setQuestion] = useState<undefined | string>(undefined);
  const [numberOfAnswers, setNumberOfAnswers] = useState<undefined | number>(
    undefined
  );
  const [notaryID, setNotaryId] = useState<undefined | string>(undefined);

  const handleSubmit = () => {
    console.log("submiting...");
    // TODO validation
    const data = {
      voting: { question, answer },
      transactionDate: new Date(),
      companyID: companyId,
      transactionAuthor: author,
      transactionType: "VotingResults",
      Status: "GIT", // ????
      priority,
      notaryID,
    };

    axios
      .post("http://localhost:8080/node/add/transaction", data)
      .then((response) => {
        if (response.status === 200 && response.data.message === "OK") {
          setMessage("Transaction added");
        } else {
          setMessage("Error while sending transaction");
        }
      })
      .catch(() => setMessage("Error while sending transaction"));
    // .finally(() => setMessage("End of sending transaction"));
  };

  return (
    <div className="p-4 bg-light">
      {!!message && <div>{message} </div>}
      <FormControl fullWidth>
        <div>Voting Results</div>
        <TextField
          className="my-2"
          id="author"
          name="author"
          label="Author"
          variant="standard"
          value={author}
          onChange={(e) => setAuthor(e.target.value)}
        />
        <TextField
          className="my-2"
          id="notary"
          name="notary"
          label="Notary ID"
          variant="standard"
          value={notaryID}
          onChange={(e) => setNotaryId(e.target.value)}
        />
        <TextField
          className="my-2"
          id="id"
          label="Company Id"
          type="number"
          variant="standard"
          value={companyId}
          onChange={(e) => setCompanyId(parseInt(e.target.value, 10))}
        />

        <TextField
          className="my-2"
          id="priority"
          label="Priority"
          type="number"
          variant="standard"
          value={priority}
          onChange={(e) => {
            let currentValue = parseInt(e.target.value, 10);

            if (currentValue > 5) currentValue = 5;
            if (currentValue < 0) currentValue = 0;

            setPriority(currentValue);
          }}
        />
        <TextField
          className="my-2"
          id="question"
          label="Question"
          variant="standard"
          value={question}
          onChange={(e) => setQuestion(e.target.value)}
        />
        <TextField
          className="my-2"
          id="numberOfAnswers"
          label="Number of answers"
          type="number"
          variant="standard"
          value={numberOfAnswers}
          onChange={(e) => {
            let currentValue = parseInt(e.target.value, 10);

            if (currentValue > 10) currentValue = 5;
            if (currentValue < 0) currentValue = 0;

            if (!answer || currentValue > answer.length) {
              setVoting([
                ...answer,
                ...new Array(currentValue - answer.length),
              ]);
            }

            setNumberOfAnswers(currentValue);
          }}
        />
        {!!numberOfAnswers &&
          answer.slice(0, numberOfAnswers).map((el, id) => (
            <div key={id}>
              {id + 1}{" "}
              <TextField
                className="mx-3"
                id="answer"
                label="Answer"
                variant="standard"
                value={el?.answer}
                onChange={(e) => {
                  const arr = [...answer];

                  arr[id] = {
                    ...arr[id],
                    answer: e.target.value,
                  };

                  setVoting(arr);
                }}
              />
              <TextField
                className="mx-3"
                id="result"
                label="Result"
                type="number"
                variant="standard"
                value={el?.result}
                onChange={(e) => {
                  const arr = [...answer];
                  arr[id] = {
                    ...arr[id],
                    result: parseInt(e.target.value, 10),
                  };
                  setVoting(arr);
                }}
              />
            </div>
          ))}
        <Button
          className="mt-4"
          type="submit"
          variant="outlined"
          color="primary"
          onClick={handleSubmit}
        >
          Add transaction
        </Button>
      </FormControl>
    </div>
  );
};

export default DividendsPayment;
