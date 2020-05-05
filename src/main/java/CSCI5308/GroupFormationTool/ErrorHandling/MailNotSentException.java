package CSCI5308.GroupFormationTool.ErrorHandling;

public class MailNotSentException extends RuntimeException {
    public MailNotSentException(String message) {
        super(message);
    }

    public MailNotSentException(String message, Throwable cause) {
        super(message, cause);
    }

}
