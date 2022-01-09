package logic.Transactions.Utilities;

import java.util.LinkedList;
import java.util.List;

public class Voting {
    public String question;
    public List<VotingAnswer> answer = new LinkedList<>();

    public Voting(String question, List<VotingAnswer> answer) {
        this.question = question;
        this.answer = answer;
    }
}
