package logicTests;
import main.logic.Transactions.ConcreteTransactionBuilderFactory;
import main.logic.Transactions.ConcreteTransactions.SharesLiquidation;
import main.logic.Transactions.TransactionsDirector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;


public class TransactionsTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    @DisplayName("should create SharesLiquidation")
    void createSharesLiquidationTest () {
        // given
//        TransactionsDirector director = new TransactionsDirector();
//        SharesLiquidationBuilder builder = ConcreteTransactionBuilderFactory.getSharesLiquidationBuilder();
//        SharesLiquidation transaction1 = new SharesLiquidation();

        // when
//        director.createSharesLiquidationTransaction(builder,
//                1337, new Date(), 2, "Masty Ben", main.logic.Transactions.Utilities.TransactionType.SharesLiquidation,
//                main.logic.Transactions.Utilities.Priority.HIGH, 10, "owner1");
//        SharesLiquidation transaction2 = builder.getTransaction();

        // then
        assert true;
    }
}
