package org.logging.abstraction;

import org.logging.implementor.LogWriter;

public abstract class Logger {

    protected final LogWriter writer;

    protected Logger(LogWriter writer) {
        this.writer = writer;
    }

    public abstract void log(String message);
}
