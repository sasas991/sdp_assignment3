package org.logging.implementor;

import org.logging.exception.LoggingException;

public interface LogWriter {

    void write(String message) throws LoggingException;
}
