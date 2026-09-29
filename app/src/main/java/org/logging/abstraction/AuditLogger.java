package org.logging.abstraction;

import org.logging.implementor.LogWriter;

public class AuditLogger extends Logger {

    public AuditLogger(LogWriter writer) {
        super(writer);
    }

    @Override
    public void log(String message) {
        writer.write("audit: " + message);
    }
}