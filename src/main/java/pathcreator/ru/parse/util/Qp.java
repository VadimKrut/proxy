package pathcreator.ru.parse.util;

import io.helidon.common.uri.UriQuery;
import io.helidon.webserver.http.ServerRequest;
import org.checkerframework.checker.nullness.qual.NonNull;

public final class Qp {

    private Qp() {
    }

    public static String get(@NonNull final ServerRequest req, @NonNull final String name) {
        final UriQuery q = req.query();
        if (q.isEmpty()) return null;
        return q.first(name).orElse(null);
    }

    public static String getTrimmed(@NonNull final ServerRequest req, @NonNull final String name) {
        final String s = get(req, name);
        if (s == null) return null;
        final String trim = s.trim();
        return trim.isEmpty() ? null : trim;
    }

    public static Integer getInt(@NonNull final ServerRequest req, @NonNull final String name) {
        final String s = getTrimmed(req, name);
        if (s == null) return null;
        try {
            return Integer.parseInt(s);
        } catch (final NumberFormatException e) {
            return null;
        }
    }

    public static Long getLong(@NonNull final ServerRequest req, @NonNull final String name) {
        final String s = getTrimmed(req, name);
        if (s == null) return null;
        try {
            return Long.parseLong(s);
        } catch (final NumberFormatException e) {
            return null;
        }
    }

    public static Boolean getBool(@NonNull final ServerRequest req, @NonNull final String name) {
        final String s = getTrimmed(req, name);
        if (s == null) return null;
        if ("true".equalsIgnoreCase(s) || "1".equals(s)) return Boolean.TRUE;
        if ("false".equalsIgnoreCase(s) || "0".equals(s)) return Boolean.FALSE;
        return null;
    }
}