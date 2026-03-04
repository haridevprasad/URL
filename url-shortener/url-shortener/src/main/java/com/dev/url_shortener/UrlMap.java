package com.dev.url_shortener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UrlMap {
    @Id
    private String shortCode;
    
    @Column(nullable = false, length = 2048)
    private String originalUrl;

    public UrlMap() {}
    public UrlMap(String shortCode, String originalUrl) {
        this.shortCode = shortCode;
        this.originalUrl = originalUrl;
    }

    public String getShortCode() { return shortCode; }
    public String getOriginalUrl() { return originalUrl; }
}