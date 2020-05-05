package CSCI5308.GroupFormationTool.ErrorHandling;

public class UserDuplicationException extends RuntimeException {
    public UserDuplicationException(String message) {
        super(message);
    }

    public UserDuplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
