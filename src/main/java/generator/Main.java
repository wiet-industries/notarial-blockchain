package generator;

public class Main {
    public static void main(String[] args) {
        BlockchainGenerator blockchainGenerator = new BlockchainGenerator(10, 3);
        blockchainGenerator.generate();
        System.out.println(blockchainGenerator.getJsonAsString());
        blockchainGenerator.writeToFile("/Users/kamil/notarial-blockchain/src/main/java/generator/Blockchain.json");
        blockchainGenerator.logDetails();
    }
}
