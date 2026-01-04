package data;

public class DAOConfig {
    private static final String STORAGE_HINT = "in-memory";

    private DAOConfig() {
    }

    public static String getStorageHint() {
        return STORAGE_HINT;
    }
}
