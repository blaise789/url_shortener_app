package com.blaisecoder.urlshortener.services.impl;

import com.blaisecoder.urlshortener.models.ShortUrl;
import com.blaisecoder.urlshortener.models.dtos.GenerateShortUrlDTO;
import com.blaisecoder.urlshortener.repositories.ShortUrlRepository;
import com.blaisecoder.urlshortener.services.IShortUrlService;
import com.blaisecoder.urlshortener.utils.ShortUrlMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ShortUrlServiceImpl implements IShortUrlService {
    //    shorturl repository
   @Autowired
    private  ShortUrlRepository shortUrlRepository;

    @Override
    public ShortUrl generateShortUrl(GenerateShortUrlDTO dto) {
        ShortUrl shortUrl= ShortUrlMapper.toEntity(dto);
// check if the short url already exists
        if(this.shortUrlRepository.existsByCustomId(dto.getCustomId())){
            log.info(" creating  the  shorturl  with customIdId  {} and longUrl {} failed ",shortUrl.getCustomId(),shortUrl.getLongUrl());

        }
        shortUrlRepository.save(shortUrl);
        return shortUrl;
    }

    @Override
    public String getLongUrl(String id) {
       ShortUrl shortUrl=shortUrlRepository.findByCustomId(id).orElseThrow(()->new Error("not found"));
        return shortUrl.getLongUrl();
    }

    @Override
    public void deleteShortUrl(String id) {
ShortUrl entity=this.shortUrlRepository.findByCustomId(id).orElseThrow();
this.shortUrlRepository.delete(entity);
log.info("Short url with customId {} is successfully deleted",id);

    }

}
