import { Button, FormControl, TextField } from "@mui/material";
import axios from "axios";
import React, { useState } from "react";

const DividendsPayment = () => {
  const [message, setMessage] = useState<string | undefined>();
  const [author, setAuthor] = useState<undefined | string>(undefined);
  const [companyId, setCompanyId] = useState<undefined | number>(undefined);
  const [priority, setPriority] = useState<undefined | number>(undefined);
  const [money, setMoney] = useState<undefined | number>(undefined);
  const [receiver, setReceiver] = useState<undefined | string>(undefined);
  const [notaryID, setNotaryId] = useState<undefined | string>(undefined);

  const handleSubmit = () => {
    console.log("submiting...");
    // TODO validation
    const data = {
      numberOfMoneyPayed: money,
      receiver,
      transactionDate: new Date(),
      companyID: companyId,
      transactionAuthor: author,
      transactionType: "DividendsPayment",
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
        <div>DividendsPayment</div>
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
          id="receiver"
          label="Receiver"
          variant="standard"
          value={receiver}
          onChange={(e) => setReceiver(e.target.value)}
        />
        <TextField
          className="my-2"
          id="money"
          label="Money"
          type="number"
          variant="standard"
          value={money}
          onChange={(e) => setMoney(parseInt(e.target.value, 10))}
        />

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
