package logic.utils;

public class EnvConfig {
    static public String getPrivateKey() {
        return System.getenv("PRIVATE_KEY");
    }
}

