package tests;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private static final int maxRetryCount = 3; // Количество попыток

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            System.out.println("Повторная попытка теста: " + retryCount);
            return true; // Повторяем тест
        }
        return false; // Больше не повторяем
    }
}
