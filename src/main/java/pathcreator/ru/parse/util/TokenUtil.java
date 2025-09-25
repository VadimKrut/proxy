package pathcreator.ru.parse.util;

import io.grpc.Metadata;
import io.helidon.http.HeaderNames;
import io.helidon.http.Headers;

public final class TokenUtil {

    private TokenUtil() {
    }

    private static final int BEARER_LEN = 7;
    private static final String BEARER = "Bearer ";
    private static final Metadata.Key<String> AUTHZ = Metadata.Key.of("authorization", Metadata.ASCII_STRING_MARSHALLER);

    public static String bearer(final Headers headers) {
        final String v = headers.first(HeaderNames.AUTHORIZATION).orElse(null);
        if (v == null || v.length() <= BEARER_LEN) return null;
        if (!v.regionMatches(true, 0, BEARER, 0, BEARER_LEN)) return null;
        return v.substring(BEARER_LEN);
    }

    public static String bearer(final Metadata md) {
        final String v = md.get(AUTHZ);
        if (v == null || v.length() <= BEARER_LEN) return null;
        return v.regionMatches(true, 0, BEARER, 0, BEARER_LEN) ? v.substring(BEARER_LEN) : null;
    }
}