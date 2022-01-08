package logic.Transactions.ConcreteTransactions;

import logic.Transactions.Utilities.TransactionType;

import java.util.Date;

public class CompanyAccountUpdate extends AbstractTransaction {
    private int ValueToAdd;

    public CompanyAccountUpdate(Date transactionDate, int companyID, String transactionAuthor,
                                TransactionType transactionType, String status, int priority, int valueToAdd) {
        super(transactionDate, companyID, transactionAuthor, transactionType, status, priority);
        this.ValueToAdd = valueToAdd;
    }

    public int getValueToAdd() {
        return ValueToAdd;
    }

    public void setValueToAdd(int valueToAdd) {
        ValueToAdd = valueToAdd;
    }

    @Override
    public String toString() {
        return "CompanyAccountUpdate{" +
                "ValueToAdd=" + ValueToAdd +
                '}';
    }
}
