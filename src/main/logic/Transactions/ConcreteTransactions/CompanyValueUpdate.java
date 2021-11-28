package main.logic.Transactions.ConcreteTransactions;

import main.logic.Transactions.AbstractTransaction;

public class CompanyValueUpdate extends AbstractTransaction {
    private int ValueToAdd;

    public int getValueToAdd() {
        return ValueToAdd;
    }

    public void setValueToAdd(int valueToAdd) {
        ValueToAdd = valueToAdd;
    }

    @Override
    public String toString() {
        return "CompanyValueUpdate{" +
                "ValueToAdd=" + ValueToAdd +
                '}';
    }
}
