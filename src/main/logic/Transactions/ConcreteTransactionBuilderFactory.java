package main.logic.Transactions;

import main.logic.Transactions.Builders.*;

public class ConcreteTransactionBuilderFactory {
    public static SharesLiquidationBuilder getSharesLiquidationBuilder() {
        return new SharesLiquidationBuilder();
    }
    public static AddCompanyBuilder getAddCompanyBuilder() {
        return new AddCompanyBuilder();
    }
    public static CompanyAccountUpdateBuilder getCompanyAccountUpdateBuilder() { return new CompanyAccountUpdateBuilder(); }
    public static CompanyValueUpdateBuilder getCompanyValueUpdateBuilder() {
        return new CompanyValueUpdateBuilder();
    }
    public static DividendsPaymentBuilder getDividendsPaymentBuilder() {
        return new DividendsPaymentBuilder();
    }
    public static NewSharesEmissionBuilder getNewSharesEmissionBuilder() {
        return new NewSharesEmissionBuilder();
    }
    public static SellBuySharesBuilder getSellBuySharesBuilder() {
        return new SellBuySharesBuilder();
    }
    public static VotingResultsBuilder getVotingResultsBuilder() {
        return new VotingResultsBuilder();
    }
}
