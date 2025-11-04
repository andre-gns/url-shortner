package com.example.url_shortener.service;

import com.example.url_shortener.model.Url;
import com.example.url_shortener.repository.UrlRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String shortenUrl(String longUrl) {

        String shortKey = generateUniqueKey();
        Url newUrl = new Url();
        newUrl.setLongUrl(longUrl);
        newUrl.setShortUrlKey(shortKey);
        newUrl.setCreatedAt(LocalDateTime.now());
        urlRepository.save(newUrl);

        return shortKey;
    }
    /**
     * @param shortKey A chave curta para buscar.
     * @return A URL longa original (ex: "https://www.google.com").
     */
    public String getLongUrlAndIncrementAccess(String shortKey) {
        Url url = urlRepository.findById(shortKey)
                .orElseThrow(() -> new RuntimeException("URL Curta não encontrada: " + shortKey));
        url.setClickCount(url.getClickCount() + 1);
        urlRepository.save(url);

        return url.getLongUrl();
    }

    private String generateUniqueKey() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}