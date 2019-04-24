package frc.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import frc.util.transports.DefaultTransport;

public class RobotLogger implements Runnable {
    private static RobotLogger mInstance;

    private List<RobotLog> mLogQueue;
    private List<RobotTransport> mTransports;
    private Thread mLogThread;
    private boolean mRunning;

    public static RobotLogger getInstance() {
        if (RobotLogger.mInstance == null) {
            RobotLogger.mInstance = new RobotLogger();
        }
        return RobotLogger.mInstance;
    }

    private RobotLogger() {
        this.mLogQueue = Collections.synchronizedList(new ArrayList<RobotLog>());
        this.mTransports = new ArrayList<>();
        this.mTransports.add(new DefaultTransport());
        this.mLogThread = new Thread(this);
        this.mLogThread.setPriority(Thread.NORM_PRIORITY);
    }

    public void addTransport(RobotTransport transport) {
        this.mTransports.add(transport);
    }

    public void log(String message) {
        this.log(RobotLogLevel.INFO, message, null);
    }

    public void log(RobotLogLevel level, String message) {
        this.log(level, message, null);
    }

    public void log(RobotLogLevel level, String message, RobotLogMetadata metadata) {
        RobotLog newLog = new RobotLog(level, message, metadata);
        this.mLogQueue.add(newLog);
        this.notifyThread();
    }

    public void error(String message) {
        this.log(RobotLogLevel.ERROR, message);
    }

    public void error(String message, RobotLogMetadata metadata) {
        this.log(RobotLogLevel.ERROR, message, metadata);
    }

    public void warn(String message) {
        this.log(RobotLogLevel.WARN, message);
    }

    public void warn(String message, RobotLogMetadata metadata) {
        this.log(RobotLogLevel.WARN, message, metadata);
    }

    public void start() {
        this.mRunning = true;
        this.mLogThread.start();
    }

    public void stop() throws InterruptedException {
        this.mRunning = false;
        this.notifyThread();
        this.mLogThread.join();
    }    

    @Override
    public void run() {
        while (this.mRunning) {
            this.logMessages();
            this.awaitMessages();
        }
    }

    private void logMessages() {
        while (this.mLogQueue.size() > 0) {
            // TODO - Account for Transports
            RobotLog log = this.mLogQueue.remove(0);
            for (RobotTransport transport : this.mTransports) {
                transport.onLog(log);
            }
        }
    }

    private void awaitMessages() {
        try {
            synchronized(this.mLogQueue) {
                this.mLogQueue.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void notifyThread() {
        synchronized (this.mLogQueue) {
            this.mLogQueue.notifyAll();
        }
    }
}