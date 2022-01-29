package node.TransactionProcess;

import logic.Company;
import logic.Transactions.ConcreteTransactions.AbstractTransaction;
import logic.Transactions.ConcreteTransactions.CompanyValueUpdate;

public class CompanyValueUpdateProcess implements TransactionProcess{
    @Override
    public void process(AbstractTransaction transaction, Company company) {
        CompanyValueUpdate companyValueUpdate = (CompanyValueUpdate) transaction;
        company.updateCompanyValue(companyValueUpdate.getValueToAdd());
        company.updateShareValue();
    }
}