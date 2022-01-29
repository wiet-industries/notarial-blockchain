import axios from "axios";
import CompanyInfo from "./CompanyInfoModel";

import serverUrl from "./Contsts";

const fetchCompanyInfo = async (id: number): Promise<CompanyInfo> =>
  axios
    .get(`${serverUrl}/node/company/info/${id}`)
    .then((response) => response.data);

export default fetchCompanyInfo;
