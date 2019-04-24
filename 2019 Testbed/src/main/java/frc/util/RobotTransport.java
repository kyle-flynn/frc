package frc.util;

import java.io.PrintStream;

/**
 * The RobotTransport abstract class is meant to be a framework for designing different transports.
 * Examples include using System.out, and a modified return of metadata in JSON format.
 */
public abstract class RobotTransport {

    protected PrintStream mStream;

    public RobotTransport(PrintStream stream) {
        this.mStream = stream;
    }

    public abstract void onLog(RobotLog log);
}