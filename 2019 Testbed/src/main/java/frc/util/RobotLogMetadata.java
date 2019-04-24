package frc.util;

import java.util.HashMap;

public class RobotLogMetadata {
    private HashMap<String, Object> mMetadata;

    public RobotLogMetadata() {
        this.mMetadata = new HashMap<>();
    }

    public void add(String key, Object value) {
        this.mMetadata.put(key, value);
    }

    public HashMap<String, Object> get() {
        return this.mMetadata;
    }
}