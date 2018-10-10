package com.vialis.vtc.tool.concurrent;

import java.nio.file.Path;

public class TraversalResult {
    private Path path;
    private long startTime;
    private long endTime;
    private long totalDiskUsageInBytes;
    private long totalFiles;
    private long totalDirectories;
    private long totalSymLinks;

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getTotalDiskUsageInBytes() {
        return totalDiskUsageInBytes;
    }

    public void setTotalDiskUsageInBytes(long totalDiskUsageInBytes) {
        this.totalDiskUsageInBytes = totalDiskUsageInBytes;
    }

    public long getTotalFiles() {
        return totalFiles;
    }

    public void setTotalFiles(long totalFiles) {
        this.totalFiles = totalFiles;
    }

    public long getTotalDirectories() {
        return totalDirectories;
    }

    public void setTotalDirectories(long totalDirectories) {
        this.totalDirectories = totalDirectories;
    }

    public long getTotalSymLinks() {
        return totalSymLinks;
    }

    public void setTotalSymLinks(long totalSymLinks) {
        this.totalSymLinks = totalSymLinks;
    }
}
