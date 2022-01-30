package blockchain.validators;

import blockchain.Transaction;
import logic.Company;

public class CompanyValueUpdateValidator implements Validator {
    @Override
    public boolean validate(Company company, Transaction transaction) {
        return false;
    }
}
