package org.logging;

import java.nio.file.Path;

import org.logging.abstraction.ApplicationLogger;
import org.logging.abstraction.AuditLogger;
import org.logging.abstraction.Logger;
import org.logging.selection.LogWriterRegistry;

public class Main {

    public static void main(String[] args) {
        String writerType;
        
        if (args.length > 0) {
            writerType = args[0];
        } else {
            writerType = "console";
        }

        LogWriterRegistry registry = new LogWriterRegistry();

        registry.register("console",
                org.logging.implementor.ConsoleWriter::new);

        registry.register("file",
                () -> new org.logging.implementor.FileWriter(
                        Path.of("application.log")
                ));

        registry.register("legacy",
                () -> new org.logging.implementor.LegacyLoggerAdapter(
                        new org.logging.legacy.LegacyLogger()
                ));

        var writer = registry.create(writerType);

        Logger applicationLogger = new ApplicationLogger(writer);
        applicationLogger.log("user logged in");

        Logger auditLogger = new AuditLogger(writer);
        auditLogger.log("user deleted an account");
    }
}