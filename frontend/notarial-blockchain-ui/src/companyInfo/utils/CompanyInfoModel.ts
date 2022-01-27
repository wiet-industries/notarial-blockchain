interface CompanyInfo {
  name: string;
  ID: number;
  shareValue: number;
  companyValue: number;
  earnings: number;
  shares: Map<string, number>;
}

export default CompanyInfo;
