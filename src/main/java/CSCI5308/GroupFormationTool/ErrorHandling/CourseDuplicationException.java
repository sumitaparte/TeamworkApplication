package CSCI5308.GroupFormationTool.ErrorHandling;

public class CourseDuplicationException extends RuntimeException {
    public CourseDuplicationException(String message) {
        super(message);
    }

    public CourseDuplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
