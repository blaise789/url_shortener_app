package com.blaisecoder.urlshortener.controllers;

import com.blaisecoder.urlshortener.models.ShortUrl;
import com.blaisecoder.urlshortener.models.dtos.GenerateShortUrlDTO;
import com.blaisecoder.urlshortener.services.IShortUrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ShortUrlController {
    private final IShortUrlService shortUrlService;
//    create shorturl
    @PostMapping
    public ResponseEntity<ShortUrl> createShortUrl(@RequestBody()GenerateShortUrlDTO generateShortUrlDTO){
        ShortUrl shortUrl=shortUrlService.generateShortUrl(generateShortUrlDTO);
        return new ResponseEntity<ShortUrl>(shortUrl, HttpStatus.CREATED);
    }
//    delete url
}
