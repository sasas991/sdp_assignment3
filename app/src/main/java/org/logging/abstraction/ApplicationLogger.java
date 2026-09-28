package org.logging.abstraction;

import org.logging.implementor.LogWriter;

public class ApplicationLogger extends Logger {

    public ApplicationLogger(LogWriter writer) {
        super(writer);
    }

    @Override
    public void log(String message) {
        writer.write("app: " + message);
    }
}
