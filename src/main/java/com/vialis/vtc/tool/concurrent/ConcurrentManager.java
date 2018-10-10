package com.vialis.vtc.tool.concurrent;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConcurrentManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConcurrentManager.class);

    public List<TraversalResult> prepareJobsAndExecute(Set<Path> paths) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(paths.size());
        List<Future<TraversalResult>> futures = executorService.invokeAll(createTasks(paths));
        LOGGER.info("About to get results...");
//        return futures.stream().parallel().map(Future::get).collect(Collectors.toList()); // Throws exceptions
        ArrayList<TraversalResult> result = new ArrayList<>();
        for (Future<TraversalResult> future : futures) {
            LOGGER.debug("Checking future: {}", future);
            try {
                TraversalResult traversalResult = future.get();
                LOGGER.debug("Adding result: {}", traversalResult);
                result.add(traversalResult);
            } catch (ExecutionException e) {
                LOGGER.error("Skipping result for future: {}", future, e);
            }
        }
        return result;
    }

    protected List<PathTraversalCallable> createTasks(Set<Path> paths) {
        LOGGER.debug("About to create tasks...");
        ArrayList<PathTraversalCallable> tasks = new ArrayList<>();
        for (Path path : paths) {
            PathTraversalCallable pathTraversalCallable = new PathTraversalCallable(path);
            tasks.add(pathTraversalCallable);
        }
        return tasks;
    }
}
