import { Button, TextField } from "@mui/material";
import React, { useState } from "react";

import CompanyInfo from "./utils/CompanyInfoModel";
import fetchCompanyInfo from "./utils/fetchComapnyInfo";

const CompanyInfoView = () => {
  const [id, setId] = useState<number>(0);
  const [isInfoVisible, setIsInfoVisible] = useState<boolean>(false);
  const [companyInfo, setCompanyInfo] = useState<CompanyInfo | null>(null);

  const handleButtonPress = async () => {
    try {
      const fetchedCompanyInfo = await fetchCompanyInfo(id);
      setCompanyInfo(fetchedCompanyInfo);
      setIsInfoVisible(true);
    } catch (err) {
      // TODO show user information about error
      console.error(err);
    }
  };

  // TODO make this as variable, not a function and use useMemo to avoid re-declaration
  // Maybe use default values or message instead of !.
  const renderCompanyInfo = () => {
    if (isInfoVisible) {
      return (
        <div>
          <p>Name: {companyInfo?.name}</p>
          <p>ID: {companyInfo?.ID}</p>
          <p>shareValue: {companyInfo?.shareValue}</p>
          <p>companyValue: {companyInfo?.companyValue}</p>
          <p>earnings: {companyInfo?.earnings}</p>
          <p>shares: </p>
          {companyInfo?.shares &&
            Object.entries(companyInfo.shares).forEach((key, value) => (
              <div>
                {key}: {value}
              </div>
            ))}
        </div>
      );
    }
    return <div />;
  };

  // NUMBER FIELD NOT TEXT FIELD
  const renderSearchForm = () => (
    <div>
      <TextField
        label="Company ID you want to search"
        variant="standard"
        onChange={(e) => setId(parseInt(e.target.value, 10))}
      />
      <Button onClick={handleButtonPress} variant="contained">
        Search
      </Button>
    </div>
  );

  return (
    <div>
      {renderSearchForm()}
      {renderCompanyInfo()}
    </div>
  );
};

export default CompanyInfoView;
