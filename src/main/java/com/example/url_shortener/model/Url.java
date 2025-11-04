package com.example.url_shortener.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Url {
    @Id
    private String ShortUrlKey;

    private String longUrl;

    private LocalDateTime createdAt;

    private Long clickCount = 0L;

    public Url() {

    }
}
