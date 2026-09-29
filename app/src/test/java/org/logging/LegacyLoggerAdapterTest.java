package org.logging;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.logging.exception.LoggingException;
import org.logging.exception.StorageLoggingException;
import org.logging.implementor.LegacyLoggerAdapter;
import org.logging.legacy.LegacyLogger;

class LegacyLoggerAdapterTest {

    @Test
    void shouldSuccessfullyWriteUsingLegacyLogger() {
        LegacyLogger legacyLogger = new LegacyLogger();
        LegacyLoggerAdapter adapter = new LegacyLoggerAdapter(legacyLogger);

        assertDoesNotThrow(() -> adapter.write("user logged in"));
    }

    @Test
    void shouldTranslateStorageError() {
        LegacyLogger legacyLogger = new LegacyLogger() {
            @Override
            public int appendEntry(String source, String text) {
                return 1;
            }
        };

        LegacyLoggerAdapter adapter = new LegacyLoggerAdapter(legacyLogger);

        assertThrows(
                StorageLoggingException.class,
                () -> adapter.write("test message")
        );
    }

    @Test
    void shouldTranslateInvalidSourceError() {
        LegacyLogger legacyLogger = new LegacyLogger() {
            @Override
            public int appendEntry(String source, String text) {
                return 2;
            }
        };

        LegacyLoggerAdapter adapter = new LegacyLoggerAdapter(legacyLogger);

        assertThrows(
                LoggingException.class,
                () -> adapter.write("test message")
        );
    }
}