import { Button, FormControl, TextField } from "@mui/material";
import axios from "axios";
import React, { useState } from "react";

const DeleteNotary = () => {
  const [message, setMessage] = useState<string | undefined>();
  const [author, setAuthor] = useState<undefined | string>(undefined);
  const [notaryID, setNotaryId] = useState<undefined | string>(undefined);
  const [priority, setPriority] = useState<undefined | number>(undefined);
  const [publicKey, setPublicKey] = useState<undefined | string>(undefined);
  const [notaryIdToDelete, setNotaryIDToDelete] = useState<undefined | string>(
    undefined
  );

  const handleSubmit = () => {
    const data = {
      transactionDate: new Date(),
      notaryID,
      transactionAuthor: author,
      transactionType: "DeleteNotary",
      notaryIdToDelete,
      priority,
      companyID: 0,
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
        <div>Delete Notary</div>
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
          id="id"
          label="Notary Id"
          variant="standard"
          value={notaryID}
          onChange={(e) => setNotaryId(e.target.value)}
        />
        <TextField
          className="my-2"
          id="id"
          label="Notary Id to Delete"
          variant="standard"
          value={notaryIdToDelete}
          onChange={(e) => setNotaryIDToDelete(e.target.value)}
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

export default DeleteNotary;
