import { Button, FormControl, TextField } from "@mui/material";
import axios from "axios";
import React, { useState } from "react";

const SharesLiquidation = () => {
  const [message, setMessage] = useState<string | undefined>();
  const [author, setAuthor] = useState<undefined | string>(undefined);
  const [companyId, setCompanyId] = useState<undefined | number>(undefined);
  const [priority, setPriority] = useState<undefined | number>(undefined);
  const [shares, setShares] = useState<undefined | number>(undefined);
  const [owner, setOwner] = useState<undefined | string>(undefined);
  const [notaryID, setNotaryId] = useState<undefined | string>(undefined);

  const handleSubmit = () => {
    console.log("submiting...");
    // TODO validation
    const data = {
      numberOfSharesToLiquidate: shares,
      owner,
      transactionDate: new Date(),
      companyID: companyId,
      transactionAuthor: author,
      transactionType: "SharesLiquidation",
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
        <div>Shares Emission</div>
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
          id="owner"
          label="Owner"
          variant="standard"
          value={owner}
          onChange={(e) => setOwner(e.target.value)}
        />
        <TextField
          className="my-2"
          id="shares"
          label="Shares"
          type="number"
          variant="standard"
          value={shares}
          onChange={(e) => setShares(parseInt(e.target.value, 10))}
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

export default SharesLiquidation;
