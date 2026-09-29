package org.logging;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.logging.abstraction.AuditLogger;
import org.logging.implementor.LogWriter;

class AuditLoggerTest {

    @Test
    void shouldDelegateMessageToWriter() {
        StubLogWriter writer = new StubLogWriter();
        AuditLogger logger = new AuditLogger(writer);

        logger.log("user deleted an account");

        assertEquals("audit: user deleted an account", writer.lastMessage);
    }

    private static class StubLogWriter implements LogWriter {

        private String lastMessage;

        @Override
        public void write(String message) {
            lastMessage = message;
        }
    }
}