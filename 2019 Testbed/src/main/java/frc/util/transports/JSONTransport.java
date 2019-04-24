package frc.util.transports;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import frc.util.RobotLog;
import frc.util.RobotTransport;

public class JSONTransport extends RobotTransport {

    public JSONTransport() throws FileNotFoundException {
        super(new PrintStream("./log.json"));
    }



    @Override
    public void onLog(RobotLog log) {
        this.mStream.println(log);
    }
}