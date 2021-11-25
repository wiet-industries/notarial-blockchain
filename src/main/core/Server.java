import static java.lang.Thread.sleep;

public class Server {
    public static void main(String[] args) throws InterruptedException {
        for(int i = 0; i < 1000; i++) {
            System.out.println("server working.. [" + i + "]");
            sleep(3000);
        }
    }
}
