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
      console.log(err);
    }
  };

  const renderCompanyInfo = () => {
    if (isInfoVisible) {
      return (
        <div>
          <p>Name: {companyInfo!.name}</p>
          <p>ID: {companyInfo!.ID}</p>
          <p>shareValue: {companyInfo!.shareValue}</p>
          <p>companyValue: {companyInfo!.companyValue}</p>
          <p>earnings: {companyInfo!.earnings}</p>
          <p>shares: </p>
          {Object.entries(companyInfo!.shares).forEach((key, value) => {
            <div>
              {key}: {value}
            </div>;
          })}
        </div>
      );
    }
    return <div />;
  };

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
