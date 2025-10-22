package org.entain.tests.retry;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * This class retry-analyzer implements TestNG retry logic for fail tests.
 */
public class RetryAnalyzer implements IRetryAnalyzer {
    private int attempt = 0;
    private final int maxAttempts =
            Integer.parseInt(System.getProperty("retry.maxAttempts", "1"));
    private final long backoffMs =
            Long.parseLong(System.getProperty("retry.backoffMs", "0"));

    /**
     * This method is automatically invoked by TestNG after a test failure
     */
    @Override
    public boolean retry(ITestResult result) {
        if (attempt < maxAttempts) {
            attempt++;
            if (backoffMs > 0) {
                try {
                    Thread.sleep(backoffMs);
                } catch (InterruptedException ignored) {
                }
            }
            System.out.printf("[RETRY] %s (attempt %d/%d)%n",
                    result.getName(), attempt, maxAttempts);
            return true;
        }
        return false;
    }
}