import { FormControl, InputLabel, MenuItem, Select } from "@mui/material";
import React, { useState } from "react";

// eslint-disable-next-line import/no-unresolved
import AddCompanyForm from "./transaction-forms/AddCompanyForm";
// eslint-disable-next-line import/no-unresolved
import AddNotary from "./transaction-forms/AddNotary";
// eslint-disable-next-line import/no-unresolved
import CompanyAccountUpdateForm from "./transaction-forms/CompanyAccountUpdateForm";
// eslint-disable-next-line import/no-unresolved
import CompanyValueUpdateForm from "./transaction-forms/CompanyValueUpdateForm";
// eslint-disable-next-line import/no-unresolved
import DeleteNotary from "./transaction-forms/DeleteNotary";
// eslint-disable-next-line import/no-unresolved
import DividendsPayment from "./transaction-forms/DividendsPayment";
// eslint-disable-next-line import/no-unresolved
import NewSharesEmission from "./transaction-forms/NewSharesEmission";
// eslint-disable-next-line import/no-unresolved
import SellBuyShares from "./transaction-forms/SellBuyShares";
// eslint-disable-next-line import/no-unresolved
import SharesLiquidation from "./transaction-forms/SharesLiquidation";
// eslint-disable-next-line import/no-unresolved
import VotingResults from "./transaction-forms/VotingResults";

const TransactionForm = () => {
  type transactionTypeEnum =
    | "add-company"
    | "company-account-update"
    | "company-value-update"
    | "dividends-payment"
    | "new-shares-emission"
    | "sell-buy-shares"
    | "shares-liquidation"
    | "voting-results"
    | "add-notary"
    | "delete-notary";

  const transactionType = [
    "add-company",
    "company-account-update",
    "company-value-update",
    "dividends-payment",
    "new-shares-emission",
    "sell-buy-shares",
    "shares-liquidation",
    "voting-results",
    "add-notary",
    "delete-notary",
  ];

  const transactionForms = {
    "add-company": <AddCompanyForm />,
    "company-account-update": <CompanyAccountUpdateForm />,
    "company-value-update": <CompanyValueUpdateForm />,
    "dividends-payment": <DividendsPayment />,
    "new-shares-emission": <NewSharesEmission />,
    "sell-buy-shares": <SellBuyShares />,
    "shares-liquidation": <SharesLiquidation />,
    "voting-results": <VotingResults />,
    "add-notary": <AddNotary />,
    "delete-notary": <DeleteNotary />,
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
