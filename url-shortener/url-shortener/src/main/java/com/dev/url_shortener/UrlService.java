package com.dev.url_shortener;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UrlService {
    private final UrlRepository urlRepository;
    private final IdGeneratorService idGeneratorService;

    public UrlService(UrlRepository urlRepository, IdGeneratorService idGeneratorService) {
        this.urlRepository = urlRepository;
        this.idGeneratorService = idGeneratorService;
    }

    public String createShortLink(String originalUrl) {
        long uniqueId = idGeneratorService.generateId();
        String shortCode = idGeneratorService.encodeToBase62(uniqueId);
        urlRepository.save(new UrlMap(shortCode, originalUrl));
        return shortCode;
    }

    @Cacheable(value = "urls", key = "#shortCode")
    public String getOriginalUrl(String shortCode) {
        return urlRepository.findById(shortCode)
                .map(UrlMap::getOriginalUrl)
                .orElseThrow(() -> new RuntimeException("URL not found!"));
    }
}