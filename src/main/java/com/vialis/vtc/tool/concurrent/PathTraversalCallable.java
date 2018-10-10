package com.vialis.vtc.tool.concurrent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vialis.vtc.tool.path.FileTreeVisitor;

public class PathTraversalCallable implements Callable<TraversalResult> {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private TraversalResult traversalResult;
    private FileTreeVisitor fileTreeVisitor = new FileTreeVisitor();

    public PathTraversalCallable(Path path) {
        this.traversalResult = new TraversalResult();
        this.traversalResult.setPath(path);
    }

    @Override
    public TraversalResult call() throws Exception {
        LOGGER.info("Starting traversal for path: {}", this.traversalResult.getPath());
        // Set start
        this.traversalResult.setStartTime(System.nanoTime());
        try {
            // Traverse tree (max depth and does not follow symbolic links)
            Files.walkFileTree(this.traversalResult.getPath(), this.fileTreeVisitor);
        } catch (IOException e) {
            LOGGER.error("Error with walking file tree ({}), but continuing", e.getMessage());
        }
        this.traversalResult.setTotalDiskUsageInBytes(this.fileTreeVisitor.getCummulativeSizeInBytes());
        // Set end
        this.traversalResult.setEndTime(System.nanoTime());
        return this.traversalResult;
    }

    /*
    private long totalDiskUsageInBytes;
    private long totalFiles;
    private long totalDirectories;
    private long totalSymLinks;
     */
}
