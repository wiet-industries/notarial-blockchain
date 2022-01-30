package logic;

import com.google.gson.Gson;
import logic.Transactions.Utilities.Voting;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Company {
    private String name;
    private final int ID;
    private int shareValue;
    private int companyValue;
    private int earnings;
    private Map<String, Integer> shares = new HashMap<>();
    private Map<String, List<Integer>> dividends = new HashMap<>();
    private List<Voting> votingResults = new LinkedList<>();

    public Company(int ID) {
        this.ID = ID;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setShareValue(int shareValue) {
        this.shareValue = shareValue;
    }

    public void setCompanyValue(int companyValue) {
        this.companyValue = companyValue;
    }

    public void setEarnings(int earnings) {
        this.earnings = earnings;
    }

    public void setShares(Map<String, Integer> shares) {
        this.shares = shares;
    }

    public void setDividends(Map<String, List<Integer>> dividends) {
        this.dividends = dividends;
    }

    public void setVotingResults(List<Voting> votingResults) {
        this.votingResults = votingResults;
    }

    // TODO: use double as shareValue
    public void updateShareValue() {
        this.shareValue = this.companyValue / this.getNumberOfShares();
    }

    public int getNumberOfShares() {
        int result = 0;
        for (Map.Entry<String, Integer> entry : this.shares.entrySet()) {
            result += entry.getValue();
        }
        return result;
    }

    public void updateCompanyValue(int value) {
        this.companyValue += value;
    }

    public void updateEarnings(int value) {
        this.earnings += value;
    }

    public void updateShares(String owner, int shareCount) {
        int oldValue = this.shares.getOrDefault(owner, 0);
        this.shares.put(owner, oldValue + shareCount);
    }

    public void transferShareBetween(String seller, String buyer, int numberOfShares) {
        this.updateShares(seller, (-1) * numberOfShares);
        this.updateShares(buyer, numberOfShares);
    }

    public void updateDividend(String owner, int value) {
        List<Integer> dividendsList = this.dividends.getOrDefault(owner, new LinkedList<>());
        dividendsList.add(value);
        this.dividends.put(owner, dividendsList);
    }

    public void addVoting(Voting voting) {
        this.votingResults.add(voting);
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}
