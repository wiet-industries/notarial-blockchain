package node.TransactionProcess;

import logic.Company;
import logic.Transactions.ConcreteTransactions.AbstractTransaction;
import logic.Transactions.ConcreteTransactions.CompanyAccountUpdate;

public class CompanyAccountUpdateProcess implements TransactionProcess {
    @Override
    public void process(AbstractTransaction transaction, Company company) {
        CompanyAccountUpdate companyAccountUpdate = (CompanyAccountUpdate) transaction;
        company.updateEarnings(companyAccountUpdate.getValueToAdd());
    }

    @Override
    public boolean validate(AbstractTransaction transaction, Company company) {
        return company != null;
    }
}
