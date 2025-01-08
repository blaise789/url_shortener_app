package com.blaisecoder.urlshortener.controllers;

import com.blaisecoder.urlshortener.exceptions.DuplicateRecordException;
import com.blaisecoder.urlshortener.exceptions.ResourceNotFoundException;
import com.blaisecoder.urlshortener.models.ShortUrl;
import com.blaisecoder.urlshortener.models.domains.ApiResponse;
import com.blaisecoder.urlshortener.models.dtos.GenerateShortUrlDTO;
import com.blaisecoder.urlshortener.services.IShortUrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.bridge.Message;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/url_shortener")
@Slf4j
@RequiredArgsConstructor
public class            ShortUrlController {
    private final IShortUrlService shortUrlService;
    private final MessageSource messageSource;
//    create shorturl
    @PostMapping
    public ResponseEntity<ShortUrl> createShortUrl(@RequestBody() GenerateShortUrlDTO generateShortUrlDTO) throws DuplicateRecordException {
        ShortUrl shortUrl=shortUrlService.generateShortUrl(generateShortUrlDTO);
        return new ResponseEntity<>(shortUrl, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> redirectToLongUrl(@PathVariable String id) throws ResourceNotFoundException {
        String response=shortUrlService.getLongUrl(id);
        log.info("Request with customId {} is redirected to long url {}",id,response);
        return new ApiResponse<>(response,messageSource.getMessage("responses.getEntitySuccess",null,LocaleContextHolder.getLocale()),HttpStatus.OK).toResponseEntity();
    }
//    delete url
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShortUrl(String id){
        shortUrlService.deleteShortUrl(id);
        log.info("ShortUrl with customId {} is successfully deleted",id);
        return new ResponseEntity<>("shorturl removed successfully",HttpStatus.OK);
    }

}
