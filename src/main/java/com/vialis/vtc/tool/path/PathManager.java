package com.vialis.vtc.tool.path;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PathManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(PathManager.class);

    /**
     * Obtain a set (of unique) paths which exists on the file system.
     * This set is the starting point for the disk usage calculation
     * as it will be performed per path.
     *
     * @param paths
     *  The paths associated with the valid set.
     *
     * @return
     *  A set of valid paths
     */
    public static Set<Path> obtainValidPaths(String[] paths) {
        Set<Path> validPaths = new HashSet<>();
        for (String rawPath : paths) {
            LOGGER.debug("Got raw path: {}", rawPath);
            Path path = FileSystems.getDefault().getPath(rawPath);
            if (!Files.exists(path)) {
                LOGGER.warn("Ignoring path as it is not existing: {}", path);
            } else {
                validPaths.add(path);
            }
        }
        return validPaths;
    }
}
