package pathcreator.ru.parse.handler;

import io.helidon.http.Status;
import io.helidon.webserver.http.ErrorHandler;
import io.helidon.webserver.http.ServerRequest;
import io.helidon.webserver.http.ServerResponse;
import pathcreator.ru.parse.pojo.ErrorResponse;

public final class AnyErrorHandler implements ErrorHandler<Throwable> {

    private static final ErrorResponse BODY = new ErrorResponse(500, "Внутренняя ошибка сервера", "UNEXPECTED_ERROR");

    @Override
    public void handle(final ServerRequest req, final ServerResponse res, final Throwable ex) {
        res.status(Status.INTERNAL_SERVER_ERROR_500).send(BODY);
    }
}