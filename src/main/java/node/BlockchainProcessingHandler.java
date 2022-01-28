package node;

import blockchain.Block;
import blockchain.Blockchain;
import blockchain.MemPool;
import blockchain.Miner;
import blockchain.helpers.UnparsedBlock;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import logic.Company;
import logic.TransactionAdapter;
import logic.Transactions.ConcreteTransactions.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BlockchainProcessingHandler {
    private final Miner miner;
    private final MemPool memPool;
    private final Blockchain blockchain;

    public BlockchainProcessingHandler() {
        //TODO this should work with DATABASE
        this.blockchain = new Blockchain();
        this.memPool = new MemPool();

        this.miner = new Miner(this.memPool, this.blockchain);
        this.miner.start();
    }

    public Blockchain getBlockchain() {
        return blockchain;
    }

    public void addTransactionToMemPool(AbstractTransaction transaction) {
        this.memPool.addTransaction(transaction);
        // TODO does it work?
        synchronized (this.miner) {
            this.miner.notify();
        }
    }

    public void handleBlockchainFromOtherNode(JsonElement unparsedBlockchain) {
        List<Block> blockchain = this.parseJsonElementToBlockList(unparsedBlockchain);

        if (blockchain.size() < this.blockchain.getBlockchain().size()) {
            return;
        }
        Block current = blockchain.get(blockchain.size() - 1);
        for (int i = blockchain.size() - 2; i >= 0; i--) {
            Block b = blockchain.get(i);
            if (b.getHash().equals(current.getPreviousHash())) {
                current = b;
            } else {
                throw new RuntimeException("Blockchain Invalid");
            }
        }
        this.blockchain.setBlockchain(blockchain);
        System.out.println(this.blockchain.getBlockchain());
        //sprawdzić poprawność otrzymanego i wybrać dłuższy
    }

    private List<Block> parseJsonElementToBlockList(JsonElement unparsedBlockchain) {
        UnparsedBlock[] unparsedBlockList = new Gson().fromJson(unparsedBlockchain.toString(), UnparsedBlock[].class);
        List<Block> blockList = new LinkedList<>();
        TransactionAdapter adapter = new TransactionAdapter();
        for (UnparsedBlock unparsedBlock : unparsedBlockList) {
            List<AbstractTransaction> parsedTransactions = new LinkedList<>();
            unparsedBlock.transactions.forEach(transaction -> {
                try {
                    adapter.createTransactionFromJson(transaction.toString());
                } catch (ClassNotFoundException e) {
                    System.out.println("Error 77Line BlockchainProcessingHandler");
                    e.printStackTrace();
                }
                parsedTransactions.add(adapter.getTransaction());
            });
            Block blockToAdd = new Block(parsedTransactions, unparsedBlock.hash, unparsedBlock.previousHash, unparsedBlock.creationDate);
            blockList.add(blockToAdd);
        }
        return blockList;
    }

    public Company getCompanyWithID(int ID) throws IllegalArgumentException {
        Company company = new Company(ID);
        for (Block block : this.blockchain.getBlockchain()) {
            for (AbstractTransaction transaction : block.getTransactions()) {
                if (transaction.getCompanyID() == ID) {
                    // TODO: Check if AddCompany transaction is the first one
                    switch (transaction.getTransactionType()) {
                        case AddCompany:
                            AddCompany addCompany = (AddCompany) transaction;
                            company.setName(addCompany.getCompanyName());
                            company.setCompanyValue(addCompany.getCompanyValue());
                            company.setEarnings(addCompany.getCompanyAccount());
                            company.setShareValue(addCompany.getShareValue());
                            company.setShares(addCompany.getDistributedShares());
                            break;
                        case CompanyValueUpdate:
                            CompanyValueUpdate companyValueUpdate = (CompanyValueUpdate) transaction;
                            company.updateCompanyValue(companyValueUpdate.getValueToAdd());
                            company.updateShareValue();
                            break;
                        case NewSharesEmission:
                            NewSharesEmission newSharesEmission = (NewSharesEmission) transaction;
                            company.updateShares(newSharesEmission.getOwner(), newSharesEmission.getNumberOfEmittedShares());
                            company.updateShareValue();
                            break;
                        case SharesBuySell:
                            SellBuyShares sellBuyShares = (SellBuyShares) transaction;
                            company.transferShareBetween(sellBuyShares.getSeller(), sellBuyShares.getBuyer(), sellBuyShares.getNumberOfShares());
                            break;
                        case SharesLiquidation:
                            SharesLiquidation sharesLiquidation = (SharesLiquidation) transaction;
                            company.updateShares(sharesLiquidation.getOwner(), (-1) * sharesLiquidation.getNumberOfSharesToLiquidate());
                            company.updateShareValue();
                            break;
                        case DividendsPayment:
                            DividendsPayment dividendsPayment = (DividendsPayment) transaction;
                            company.updateDividend(dividendsPayment.getReceiver(), dividendsPayment.getNumberOfMoneyPayed());
                            company.updateEarnings((-1) * dividendsPayment.getNumberOfMoneyPayed());
                            break;
                        case VotingResults:
                            VotingResults votingResults = (VotingResults) transaction;
                            company.addVoting(votingResults.getVoting());
                            break;
                        case CompanyAccountUpdate:
                            CompanyAccountUpdate companyAccountUpdate = (CompanyAccountUpdate) transaction;
                            company.updateEarnings(companyAccountUpdate.getValueToAdd());
                            break;
                    }
                }
            }
        }
        // TODO: Check if company with given id was found
        return company;
    }

//   public String getCompanyName(String ID) throws IllegalArgumentException {
//
//    }

}
