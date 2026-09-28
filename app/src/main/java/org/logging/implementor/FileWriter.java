package org.logging.implementor;

import org.logging.exception.StorageLoggingException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileWriter implements LogWriter {

    private final Path file;

    public FileWriter(Path file) {
        this.file=file;
    }

    @Override
    public void write(String message) {
        try {
            Files.writeString(
                    file,
                    message + System.lineSeparator(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            throw new StorageLoggingException(
                    "failed to write log to file: " + file
            );
        }
    }
}
