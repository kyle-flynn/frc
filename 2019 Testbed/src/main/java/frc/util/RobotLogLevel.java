package frc.util;

public enum RobotLogLevel {
    ERROR(5),
    WARN(4),
    INFO(3),
    DEBUG(2);

    private int mLevel;

    RobotLogLevel(int level) {
        this.mLevel = level;
    }

    public int getLevel() {
        return this.mLevel;
    }
}