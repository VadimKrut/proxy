package pathcreator.ru.parse.exception;

import io.helidon.http.Status;

public final class ApiException extends RuntimeException {

    private final int errorCode;
    private final String details;
    private final Status status;

    public ApiException(final Status status, final String message, final String details) {
        super(message);
        this.status = status;
        this.errorCode = status().code();
        this.details = details;
    }

    public int errorCode() {
        return errorCode;
    }

    public String details() {
        return details;
    }

    public Status status() {
        return status;
    }
}