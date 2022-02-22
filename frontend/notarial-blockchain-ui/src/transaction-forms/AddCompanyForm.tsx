import { Button, FormControl, TextField } from "@mui/material";
import axios from "axios";
import React, { useState } from "react";

type HolderType = {
  name?: string;
  shares?: number;
};

const AddCompanyForm = () => {
  const [author, setAuthor] = useState<undefined | string>(undefined);
  const [companyId, setCompanyId] = useState<undefined | number>(undefined);
  const [priority, setPriority] = useState<undefined | number>(undefined);
  const [companyName, setCompanyName] = useState<undefined | string>(undefined);
  const [notaryID, setNotaryId] = useState<undefined | string>(undefined);
  const [companyValue, setCompanyValue] = useState<undefined | number>(
    undefined
  );
  const [companyAccount, setCompanyAccount] = useState<undefined | number>(
    undefined
  );
  const [shareValue, setshareValue] = useState<undefined | number>(undefined);
  const [shareHoldersNumber, setShareHoldersNumber] = useState<
    undefined | number
  >(undefined);
  const [holders, setHolders] = useState<(HolderType | undefined)[]>([]);

  const [message, setMessage] = useState<string | undefined>();

  const handleSubmit = () => {
    interface HolderInterface {
      [key: string]: number;
    }

    const obj: HolderInterface = {};
    // eslint-disable-next-line no-restricted-syntax
    for (const x of holders.slice(0, shareHoldersNumber)) {
      if (x?.name && x?.shares) {
        obj[x.name] = x.shares;
      }
    }
    const data = {
      companyName,
      companyValue,
      companyAccount,
      shareValue,
      distributedShares: obj,
      transactionDate: new Date(),
      companyID: companyId,
      transactionAuthor: author,
      transactionType: "AddCompany",
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
        <div>Adding company</div>
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
          id="name"
          label="Company Name"
          variant="standard"
          value={companyName}
          onChange={(e) => setCompanyName(e.target.value)}
        />
        <TextField
          className="my-2"
          id="companyValue"
          label="Company Value"
          type="number"
          variant="standard"
          value={companyValue}
          onChange={(e) => setCompanyValue(parseInt(e.target.value, 10))}
        />
        <TextField
          className="my-2"
          id="companyAccount"
          label="Company Account"
          type="number"
          variant="standard"
          value={companyAccount}
          onChange={(e) => setCompanyAccount(parseInt(e.target.value, 10))}
        />
        <TextField
          className="my-2"
          id="shareValue"
          label="Share Value"
          type="number"
          variant="standard"
          value={shareValue}
          onChange={(e) => setshareValue(parseInt(e.target.value, 10))}
        />
        <TextField
          className="my-2"
          id="shareHolders"
          label="Share Holders"
          type="number"
          variant="standard"
          value={shareHoldersNumber}
          onChange={(e) => {
            let currentValue = parseInt(e.target.value, 10);

            if (currentValue > 10) currentValue = 10;
            if (currentValue < 0) currentValue = 0;

            if (!holders || currentValue > holders.length) {
              setHolders([
                ...holders,
                ...new Array(currentValue - holders.length),
              ]);
            }

            setShareHoldersNumber(currentValue);
          }}
        />
        {!!shareHoldersNumber &&
          holders.slice(0, shareHoldersNumber).map((el, id) => (
            <div key={id}>
              {id + 1}{" "}
              <TextField
                className="mx-3"
                id="holderName"
                label="Holder Name"
                variant="standard"
                value={el?.name}
                onChange={(e) => {
                  const arr = [...holders];

                  arr[id] = {
                    ...arr[id],
                    name: e.target.value,
                  };

                  setHolders(arr);
                }}
              />
              <TextField
                className="mx-3"
                id="holderShares"
                label="Holder's Shares"
                type="number"
                variant="standard"
                value={el?.shares}
                onChange={(e) => {
                  const arr = [...holders];
                  arr[id] = {
                    ...arr[id],
                    shares: parseInt(e.target.value, 10),
                  };
                  setHolders(arr);
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

export default AddCompanyForm;
