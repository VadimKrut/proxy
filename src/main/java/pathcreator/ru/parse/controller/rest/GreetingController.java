package pathcreator.ru.parse.controller.rest;

import io.helidon.webserver.http.HttpRules;
import io.helidon.webserver.http.HttpService;
import pathcreator.ru.parse.controller.rest.handler.GreetingHandler;

public final class GreetingController implements HttpService {

    @Override
    public void routing(final HttpRules httpRules) {
        httpRules.get("/greeting", GreetingHandler.greeting);
    }
}