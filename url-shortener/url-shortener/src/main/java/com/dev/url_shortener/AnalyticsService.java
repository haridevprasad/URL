package com.dev.url_shortener;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AnalyticsService {
    @Async
    public void logClick(String shortCode, String userAgent, String ipAddress) {
        System.out.println("--- ASYNC ANALYTICS SAVED ---");
        System.out.println("Code Clicked: " + shortCode);
        System.out.println("-----------------------------");
    }
}
