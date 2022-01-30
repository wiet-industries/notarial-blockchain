package blockchain.validators;

import blockchain.Transaction;
import logic.Company;

public class ValidatorContext {

    private Validator validator;

    public ValidatorContext() {}

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    public void validate(Company company, Transaction transaction) {
        this.validator.validate(company, transaction);
    }
}
