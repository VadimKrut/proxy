package pathcreator.ru.parse.service;

import io.helidon.http.Status;
import pathcreator.ru.parse.exception.ApiException;

public final class GreetingService {

    private GreetingService() {
    }

    public static String greeting(final String value, final String token) {
        if (value == null || value.isBlank()) {
            throw new ApiException(Status.BAD_REQUEST_400, "Значение не может быть пустым", "VALUE_IS_EMPTY");
        }
        if (token == null || token.isBlank()) {
            throw new ApiException(Status.UNAUTHORIZED_401, "Токен обязателен", "TOKEN_REQUIRED");
        }
        return value;
    }
}