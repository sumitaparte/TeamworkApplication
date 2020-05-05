package CSCI5308.GroupFormationTool.ErrorHandling;

public class DatabaseColumnNotFoundException extends RuntimeException {
    public DatabaseColumnNotFoundException(String message) {
        super(message);
    }

    public DatabaseColumnNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
