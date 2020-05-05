package CSCI5308.GroupFormationTool.ErrorHandling;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class CustomErrorResponse {
    private final String message;
    private final HttpStatus httpStatus;
    private final String request;
    private final ZonedDateTime timestamp;

    public CustomErrorResponse(String message, HttpStatus httpStatus, String request, ZonedDateTime timestamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.request = request;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getRequest() {
        return request;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
}
