package core.utils;

public class EnvConfig {
    static public String getAuthorizedUsersAddressesFilePath() {
        return System.getenv("AUTHORIZED_USERS_PATH");
    }
}
