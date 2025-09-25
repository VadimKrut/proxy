package pathcreator.ru.parse.controller.rest.handler;

import io.helidon.webserver.http.Handler;
import io.helidon.webserver.http.ServerRequest;
import io.helidon.webserver.http.ServerResponse;
import pathcreator.ru.parse.service.GreetingService;
import pathcreator.ru.parse.util.Qp;
import pathcreator.ru.parse.util.TokenUtil;

public final class GreetingHandler {

    private GreetingHandler() {
    }

    public static final Handler greeting = new Handler() {
        @Override
        public void handle(final ServerRequest req, final ServerResponse res) {
            final String token = TokenUtil.bearer(req.headers());
            final String value = Qp.get(req, "value");
            res.send(GreetingService.greeting(value, token));
        }
    };
}