package generator;

import blockchain.Block;
import blockchain.Blockchain;
import blockchain.helpers.SHA256;
import logic.Company;
import logic.Transactions.ConcreteTransactions.AbstractTransaction;
import logic.Transactions.ConcreteTransactions.AddCompany;
import logic.Transactions.Utilities.TransactionType;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class BlockchainGenerator {
    private final int blocksNumber;
    private final int transactionsInBlockNumber;
    private final Blockchain blockchain;
    private final List<AbstractTransaction> transactions;
    private final List<Company> companies;


    public BlockchainGenerator(int blocks, int transactionsInBlock) {
        this.blocksNumber = blocks;
        this.transactionsInBlockNumber = transactionsInBlock;
        this.blockchain = new Blockchain();
        this.transactions = new LinkedList<>();
        this.companies = new LinkedList<>();
    }

    private Map<String, Integer> getShares(int shareHolders) {
        Map<String, Integer> shares = new HashMap<>();
        for (int i = 0; i < shareHolders; i++) {
            shares.put(GeneratorUtils.getRandomString(), GeneratorUtils.getRandomInt(1, 100));
        }
        return shares;
    }

    private void generateCompanies(int companiesCount) {
        for (int i = 1; i <= companiesCount; i++) {
            Company company = new Company(i);
            company.setName("company-" + GeneratorUtils.getRandomString());
            company.setShareValue(GeneratorUtils.getRandomInt(20, 3400));
            company.setCompanyValue(GeneratorUtils.getRandomInt(100, 10000));
            company.setEarnings(GeneratorUtils.getRandomInt(100, 10000));
            company.setShares(getShares(GeneratorUtils.getRandomInt(1, 10)));
            companies.add(company);
        }
    }

    private boolean checkIsValid() {

        return false;
    }


    private void generateTransactions() {
        int COMPANIES = 10;
        this.generateCompanies(COMPANIES);
        for (Company company : companies) {
            String hash = "hashhhhhh"; //TODO do sth with this
            AbstractTransaction transaction = new AddCompany(
                    new java.util.Date(),
                    company.getID(),
                    "xsan",
                    TransactionType.AddCompany,
                    "notaryID",
                    5,
                    company.getName(),
                    company.getCompanyValue(),
                    company.getEarnings(),
                    company.getShareValue(),
                    company.getShares(),
                    hash,
                    "siema"
            );
            transactions.add(transaction);
        }


    }

    private void generateBlockchain() {
        for (int i = 0; i < transactions.size(); i += transactionsInBlockNumber) {
            List<AbstractTransaction> transactionsForBlock = new ArrayList<>();
            for (int k = i; k < i + transactionsInBlockNumber && k < transactions.size(); k++) {
                transactionsForBlock.add(transactions.get(k));
            }
            Block block = blockchain.getNewBlock();
            block.setCreationDate(new java.util.Date());
            block.setPreviousHash(blockchain.getLastBlockHash());
            block.setTransactions(transactionsForBlock);
            String dataInString = block.getCreationDate() + block.transactionsToJson() + block.getPreviousHash();
            block.setHash(SHA256.generateHash(dataInString));
            this.blockchain.addAndValidateBlock(block);
        }
    }

    public void generate() {
        // generate
        System.out.println(GeneratorUtils.getRandomInt());
        System.out.println(GeneratorUtils.getRandomInt(4));
        System.out.println(GeneratorUtils.getRandomInt(5, 7));
        System.out.println(GeneratorUtils.getRandomString());
        System.out.println(GeneratorUtils.getRandomString(7));

        this.generateTransactions();
        this.generateBlockchain();

    }

    public void writeToFile(String path) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(this.getJsonAsString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getJsonAsString() {
        return blockchain.getBlockchainStringJson();
    }

    public void logDetails() {
        System.out.println("Blocks: " + blockchain.getBlockchain().size());
        System.out.println("Transactions in each block: " + transactions.size() / blockchain.getBlockchain().size());
        System.out.println("Companies: " + companies.size());
        System.out.println("Companies id's range: " + companies.get(0).getID() + " - " + companies.get(companies.size() - 1).getID());
    }
}
