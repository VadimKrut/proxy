package pathcreator.ru.parse.pojo;

public record ErrorResponse(int errorCode, String message, String details) {
}