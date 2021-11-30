package main.logic.Transactions.ConcreteTransactions;

import main.logic.Transactions.Utilities.Priority;
import main.logic.Transactions.Utilities.TransactionType;

import java.util.Date;

public class CompanyAccountUpdate extends AbstractTransaction {
    private int ValueToAdd;

    public CompanyAccountUpdate(long transactionHash, Date transactionDate, int companyID, String transactionAuthor,
                                TransactionType transactionType, String status, Priority priority, int valueToAdd) {
        super(transactionHash, transactionDate, companyID, transactionAuthor, transactionType, status, priority);
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
