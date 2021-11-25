package logic.Transactions;

import main.logic.Transactions.Builders.SharesLiquidationBuilder;

public class ConcreteTransactionBuilderFactory {
    public static SharesLiquidationBuilder getSharesLiquidationBuilder() {
        return new SharesLiquidationBuilder();
    }
}
