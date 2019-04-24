package frc.util;

public class RobotLog {

    private RobotLogLevel mLogLevel;
    private String mMessage;
    private RobotLogMetadata mMetadata;

    public RobotLog(String message) {
        this(RobotLogLevel.INFO, message, null);
    }

    public RobotLog(RobotLogLevel logLevel, String message) {
        this(logLevel, message, null);
    }

    public RobotLog(String message, RobotLogMetadata metadata) {
        this(RobotLogLevel.INFO, message, metadata);
    }

    public RobotLog(RobotLogLevel logLevel, String message, RobotLogMetadata metadata) {
        this.mLogLevel = logLevel;
        this.mMessage = message;
        this.mMetadata = metadata;
    }

    public RobotLogLevel getLogLevel() {
        return this.mLogLevel;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public RobotLogMetadata getMetadat() {
        return this.mMetadata;
    }

    @Override
    public String toString() {
        return "[" + this.mLogLevel.toString() + "] " + this.mMessage;
    }
}