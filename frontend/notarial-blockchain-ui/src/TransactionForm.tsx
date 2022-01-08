import { FormControl, InputLabel, MenuItem, Select } from "@mui/material";
import React, { useState } from "react";

// eslint-disable-next-line import/no-unresolved
import AddCompanyForm from "./transaction-forms/AddCompanyForm";

const TransactionForm = () => {
  type transactionTypeEnum =
    | "add-company"
    | "company-account-update"
    | "company-value-update"
    | "dividends-payment"
    | "new-shares-emission"
    | "sell-buy-shares"
    | "shares-liquidation"
    | "voting-results";

  const transactionType = [
    "add-company",
    "company-account-update",
    "company-value-update",
    "dividends-payment",
    "new-shares-emission",
    "sell-buy-shares",
    "shares-liquidation",
    "voting-results",
  ];

  const transactionForms = {
    "add-company": <AddCompanyForm />,
    "company-account-update": <div>company-account-update</div>,
    "company-value-update": <div>company-value-update</div>,
    "dividends-payment": <div>dividends-payment</div>,
    "new-shares-emission": <div>new-shares-emission</div>,
    "sell-buy-shares": <div>sell-buy-shares</div>,
    "shares-liquidation": <div>shares-liquidation</div>,
    "voting-results": <div>voting-results</div>,
  };

  const [currentTransaction, setCurrentTransaction] = useState(
    transactionType[0]
  );

  return (
    <div className="p-4 bg-light">
      <FormControl fullWidth>
        <InputLabel id="demo-simple-select-label">Transaction Type</InputLabel>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          value={currentTransaction}
          label="Transaction Type"
          onChange={(e) => setCurrentTransaction(e.target.value)}
        >
          {transactionType.map((transaction, id) => (
            <MenuItem value={transaction} key={id}>
              {transaction}
            </MenuItem>
          ))}
        </Select>
      </FormControl>
      <div className="text-primary">
        {transactionForms[currentTransaction as transactionTypeEnum]}
      </div>
    </div>
  );
};

export default TransactionForm;
