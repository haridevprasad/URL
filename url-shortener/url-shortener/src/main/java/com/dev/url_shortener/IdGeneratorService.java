package com.dev.url_shortener;
import org.springframework.stereotype.Component;

@Component
public class IdGeneratorService {
    private static final String BASE62_ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE = BASE62_ALPHABET.length();
    private static final long CUSTOM_EPOCH = 1609459200000L; 
    private long lastTimestamp = -1L;
    private long sequence = 0L;

    public synchronized long generateId() {
        long currentTimestamp = System.currentTimeMillis();
        if (currentTimestamp == lastTimestamp) {
            sequence = (sequence + 1) & 4095; 
            if (sequence == 0) {
                while (currentTimestamp <= lastTimestamp) {
                    currentTimestamp = System.currentTimeMillis();
                }
            }
        } else {
            sequence = 0L;
        }
        lastTimestamp = currentTimestamp;
        return ((currentTimestamp - CUSTOM_EPOCH) << 12) | sequence;
    }

    public String encodeToBase62(long id) {
        StringBuilder sb = new StringBuilder();
        while (id > 0) {
            sb.append(BASE62_ALPHABET.charAt((int) (id % BASE)));
            id /= BASE;
        }
        return sb.reverse().toString();
    }
}