package com.blaisecoder.urlshortener.services;

import com.blaisecoder.urlshortener.exceptions.ResourceNotFoundException;
import com.blaisecoder.urlshortener.models.ShortUrl;
import com.blaisecoder.urlshortener.models.dtos.GenerateShortUrlDTO;

public interface IShortUrlService {
    public ShortUrl generateShortUrl(GenerateShortUrlDTO dto);
    public String  getLongUrl(String id) throws ResourceNotFoundException;
    public void deleteShortUrl(String id);
}
