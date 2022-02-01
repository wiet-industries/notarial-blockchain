package node.TransactionProcess;

import logic.Company;
import logic.Transactions.ConcreteTransactions.AbstractTransaction;
import logic.Transactions.ConcreteTransactions.CompanyValueUpdate;

public class CompanyValueUpdateProcess implements TransactionProcess {
    @Override
    public void process(AbstractTransaction transaction, Company company) {
        CompanyValueUpdate companyValueUpdate = (CompanyValueUpdate) transaction;
        company.updateCompanyValue(companyValueUpdate.getValueToAdd());
        company.updateShareValue();
    }

    @Override
    public boolean validate(AbstractTransaction transaction, Company company) {
        CompanyValueUpdate companyValueUpdate = (CompanyValueUpdate) transaction;
        return company != null && company.getCompanyValue() + companyValueUpdate.getValueToAdd() >= 0;
    }
}
