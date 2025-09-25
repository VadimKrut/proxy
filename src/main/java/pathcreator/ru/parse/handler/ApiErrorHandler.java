package pathcreator.ru.parse.handler;

import io.helidon.webserver.http.ErrorHandler;
import io.helidon.webserver.http.ServerRequest;
import io.helidon.webserver.http.ServerResponse;
import pathcreator.ru.parse.pojo.ErrorResponse;
import pathcreator.ru.parse.exception.ApiException;

public final class ApiErrorHandler implements ErrorHandler<ApiException> {
    @Override
    public void handle(final ServerRequest req, final ServerResponse res, final ApiException ex) {
        res.status(ex.status()).send(new ErrorResponse(ex.errorCode(), ex.getMessage(), ex.details()));
    }
}