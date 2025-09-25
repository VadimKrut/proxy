package pathcreator.ru.parse.util;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class EnvPlaceholders {

    private static final Pattern P = Pattern.compile("\\$\\{([A-Za-z0-9_\\.]+):([^}]+)}");

    private EnvPlaceholders() {
    }

    public static String resolve(final String input) {
        if (input == null) return null;
        final Matcher m = P.matcher(input);
        final StringBuffer sb = new StringBuffer();
        while (m.find()) {
            final String name = m.group(1);
            final String def = m.group(2);
            final String val = Optional.ofNullable(System.getenv(name)).orElse(def);
            m.appendReplacement(sb, Matcher.quoteReplacement(val));
        }
        m.appendTail(sb);
        return sb.toString();
    }

    public static int resolveInt(final String input) {
        final String v = resolve(input);
        try {
            return Integer.parseInt(v.trim());
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException("Invalid int after placeholder resolution: '" + v + "'", e);
        }
    }
}