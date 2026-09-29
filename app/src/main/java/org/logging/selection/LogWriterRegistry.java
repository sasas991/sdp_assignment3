package org.logging.selection;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.logging.implementor.LogWriter;

public class LogWriterRegistry {

    private final Map<String, Supplier<LogWriter>> writers = new HashMap<>();

    public void register(String type, Supplier<LogWriter> factory) {
        writers.put(type, factory);
    }

    public LogWriter create(String type) {
        Supplier<LogWriter> factory = writers.get(type);

        if (factory == null) {
            throw new IllegalArgumentException(
                    "unknown log writer type: " + type
            );
        }

        return factory.get();
    }
}