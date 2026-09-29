package org.logging;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.logging.abstraction.ApplicationLogger;
import org.logging.implementor.LogWriter;

class ApplicationLoggerTest {

    @Test
    void shouldDelegateMessageToWriter() {
        StubLogWriter writer = new StubLogWriter();
        ApplicationLogger logger = new ApplicationLogger(writer);

        logger.log("user logged in");

        assertEquals("app: user logged in", writer.lastMessage);
    }

    private static class StubLogWriter implements LogWriter {

        private String lastMessage;

        @Override
        public void write(String message) {
            lastMessage = message;
        }
    }
}