package node.TransactionProcess;

import logic.Company;
import logic.Transactions.ConcreteTransactions.AbstractTransaction;
import logic.Transactions.ConcreteTransactions.AddCompany;

public class AddCompanyTransactionProcess implements TransactionProcess {

    @Override
    public void process(AbstractTransaction transaction, Company company) {
        AddCompany addCompany = (AddCompany) transaction;
        company.setName(addCompany.getCompanyName());
        company.setCompanyValue(addCompany.getCompanyValue());
        company.setEarnings(addCompany.getCompanyAccount());
        company.setShareValue(addCompany.getShareValue());
        company.setShares(addCompany.getDistributedShares());
    }

    @Override
    public boolean validate(AbstractTransaction transaction, Company company) {
        return true;
    }
}
