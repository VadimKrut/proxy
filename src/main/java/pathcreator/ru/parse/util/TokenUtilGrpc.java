package pathcreator.ru.parse.util;

public final class TokenUtilGrpc {

    private TokenUtilGrpc() {
    }

    public static final io.grpc.Context.Key<String> TOKEN = io.grpc.Context.key("authz-token");

    public static String currentToken() {
        return TOKEN.get();
    }
}