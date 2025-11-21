package logging;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
	public static void main(String[] args) {

        final Logger logger = LogManager.getLogger(Main.class);

        logger.info("Threading application started");
        logger.info("Application started at: {}", new java.util.Date());

        int cores = Runtime.getRuntime().availableProcessors();
        logger.info("Available CPU cores: {}", cores);

        logger.info("Creating thread pool with {} threads", cores);
        ExecutorService service = Executors.newFixedThreadPool(cores);
        
        
        for (int i = 1; i <= 3; i++) {
            Worker worker = new Worker();
            worker.setName("Worker-" + i);
            logger.debug("Submitting worker task {}", i);
            service.execute(worker);
        }

        logger.info("All tasks submitted to thread pool");
        service.shutdown();

        try {
            // Wait for all tasks to complete
            if (service.awaitTermination(30, TimeUnit.SECONDS)) {
                logger.info("All tasks completed successfully");
            } else {
                logger.warn("Tasks did not complete within timeout");
                service.shutdownNow();
            }
        } catch (InterruptedException e) {
            logger.error("Main thread was interrupted while waiting for tasks to complete", e);
            service.shutdownNow();
            Thread.currentThread().interrupt();
        }

        logger.info("Threading application finished");
    }
}
