package frc.util.transports;

import frc.util.RobotLog;
import frc.util.RobotTransport;

public class DefaultTransport extends RobotTransport {
    public DefaultTransport() {
        super(System.out);
    }

    @Override
    public void onLog(RobotLog log) {
        this.mStream.println(log);
    }
}