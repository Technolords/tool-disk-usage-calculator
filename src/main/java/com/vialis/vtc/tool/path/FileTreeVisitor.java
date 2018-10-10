package com.vialis.vtc.tool.path;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileTreeVisitor implements FileVisitor<Path> {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private long cummulativeSizeInBytes;

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        long size = Files.size(file);
        LOGGER.debug("Got file: {} -> size: {}", file.toString(), size);
        this.cummulativeSizeInBytes += size;
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        LOGGER.debug("File {} visit failed with {}, but proceeding...", file.toString(), exc.getMessage());
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        if (exc != null) {
            LOGGER.warn("Directory {} visit failed with {}, but proceeding...", dir.toString(), exc.getMessage());
        }
        return FileVisitResult.CONTINUE;
    }

    public long getCummulativeSizeInBytes() {
        return cummulativeSizeInBytes;
    }
}
