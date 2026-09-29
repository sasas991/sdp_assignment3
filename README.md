# Logging System
Aldiyar Kairollin

SE-2539

Adapter and Bridge Patterns

A simple Java logging system demonstrating the Bridge and Adapter design patterns.

## features

- bridge pattern with ApplicationLogger and AuditLogger
- 3 LogWriter implementations:
  - console
  - file
  - legacy adapter
- adapter for an incompatible legacy logger
- dynamic implementor selection at runtime
- junit 5 tests

## run

run tests:

./gradlew test



run the application:

./gradlew run --args="console"

./gradlew run --args="file"

./gradlew run --args="legacy"
