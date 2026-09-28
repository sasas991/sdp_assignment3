package org.logging.implementor;

import org.logging.exception.LoggingException;
import org.logging.exception.StorageLoggingException;
import org.logging.legacy.LegacyLogger;

public class LegacyLoggerAdapter implements LogWriter {

    private final LegacyLogger legacyLogger;

    public LegacyLoggerAdapter(LegacyLogger legacyLogger) {
        this.legacyLogger = legacyLogger;
    }

    @Override
    public void write(String message) {
        int result = legacyLogger.appendEntry("APPLICATION", message);

        if (result == 1) {
            throw new StorageLoggingException(
                    "legacy logger storage error"
            );
        }

        if (result == 2) {
            throw new LoggingException(
                    "legacy logger received an invalid source"
            );
        }
    }
}
