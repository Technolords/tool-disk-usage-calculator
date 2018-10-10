package com.vialis.vtc.tool;

import java.nio.file.Path;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vialis.vtc.tool.concurrent.ConcurrentManager;
import com.vialis.vtc.tool.concurrent.TraversalResult;
import com.vialis.vtc.tool.path.PathManager;

public class DiskUsageCalculator {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiskUsageCalculator.class);
    private ConcurrentManager concurrentManager = new ConcurrentManager();

    public void configureAndRun(String[] args) throws InterruptedException {
        LOGGER.info("About to calculate disk usage...");
        Set<Path> validPaths = PathManager.obtainValidPaths(args);
        LOGGER.info("Total valid paths: {}", validPaths.size());
        long startTime = System.nanoTime();
        List<TraversalResult> results = this.concurrentManager.prepareJobsAndExecute(validPaths);
        long endTime = System.nanoTime();
        for(TraversalResult result : results) {
            LOGGER.info("Path {} -> Total disk usage (in bytes): {} took: {} ns", result.getPath(), result.getTotalDiskUsageInBytes(), (result.getEndTime() - result.getStartTime()));
        }
        LOGGER.info("...done (total took {} ns), terminating", endTime -startTime);
        System.exit(0);
    }

    public static void main(String[] args) throws InterruptedException {
        DiskUsageCalculator diskUsageCalculator = new DiskUsageCalculator();
        diskUsageCalculator.configureAndRun(args);
    }
}
