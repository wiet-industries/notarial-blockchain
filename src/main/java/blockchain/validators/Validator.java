package blockchain.validators;

import blockchain.Transaction;
import logic.Company;

public interface Validator {
    boolean validate(Company company, Transaction transaction);
}
