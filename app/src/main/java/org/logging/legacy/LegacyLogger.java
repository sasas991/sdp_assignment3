package org.logging.legacy;

public class LegacyLogger {

    public int appendEntry(String source, String text) {

        if (source == null || source.isBlank()) {
            return 2;
        }

        if (text == null || text.isBlank()) {
            return 1;
        }

        return 0;
    }
}
