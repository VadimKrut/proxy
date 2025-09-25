package pathcreator.ru.parse.mapper;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import pathcreator.ru.parse.exception.ApiException;

public final class GrpcStatusCode {

    private GrpcStatusCode() {
    }

    public static StatusRuntimeException toGrpc(final ApiException ex) {
        final Status s;
        final int code = ex.status().code();
        s = switch (code) {
            case 400 -> Status.INVALID_ARGUMENT;
            case 401 -> Status.UNAUTHENTICATED;
            case 403 -> Status.PERMISSION_DENIED;
            case 404 -> Status.NOT_FOUND;
            case 405 -> Status.UNIMPLEMENTED;
            case 409 -> Status.ALREADY_EXISTS;
            case 429 -> Status.RESOURCE_EXHAUSTED;
            case 500 -> Status.INTERNAL;
            case 503 -> Status.UNAVAILABLE;
            default -> Status.UNKNOWN;
        };
        final String desc = (ex.getMessage() == null ? "" : ex.getMessage())
                + (ex.details() == null ? "" : "|" + ex.details());
        return s.withDescription(desc).asRuntimeException();
    }
}