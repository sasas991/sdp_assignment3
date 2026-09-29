package org.logging;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.logging.implementor.ConsoleWriter;
import org.logging.implementor.FileWriter;
import org.logging.implementor.LegacyLoggerAdapter;
import org.logging.implementor.LogWriter;
import org.logging.legacy.LegacyLogger;
import org.logging.selection.LogWriterRegistry;

class LogWriterRegistryTest {

    @Test
    void shouldCreateConsoleWriter() {
        LogWriterRegistry registry = createRegistry();

        LogWriter writer = registry.create("console");

        assertInstanceOf(ConsoleWriter.class, writer);
    }

    @Test
    void shouldCreateFileWriter() {
        LogWriterRegistry registry = createRegistry();

        LogWriter writer = registry.create("file");

        assertInstanceOf(FileWriter.class, writer);
    }

    @Test
    void shouldCreateLegacyAdapter() {
        LogWriterRegistry registry = createRegistry();

        LogWriter writer = registry.create("legacy");

        assertInstanceOf(LegacyLoggerAdapter.class, writer);
    }

    @Test
    void shouldRejectUnknownWriterType() {
        LogWriterRegistry registry = createRegistry();

        assertThrows(
                IllegalArgumentException.class,
                () -> registry.create("unknown")
        );
    }

    private LogWriterRegistry createRegistry() {
        LogWriterRegistry registry = new LogWriterRegistry();

        registry.register("console", ConsoleWriter::new);

        registry.register(
                "file",
                () -> new FileWriter(Path.of("application.log"))
        );

        registry.register(
                "legacy",
                () -> new LegacyLoggerAdapter(new LegacyLogger())
        );

        return registry;
    }
}