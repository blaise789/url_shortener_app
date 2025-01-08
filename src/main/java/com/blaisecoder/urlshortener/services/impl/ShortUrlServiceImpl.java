package com.blaisecoder.urlshortener.services.impl;

import com.blaisecoder.urlshortener.exceptions.DuplicateRecordException;
import com.blaisecoder.urlshortener.exceptions.ResourceNotFoundException;
import com.blaisecoder.urlshortener.models.ShortUrl;
import com.blaisecoder.urlshortener.models.dtos.GenerateShortUrlDTO;
import com.blaisecoder.urlshortener.repositories.ShortUrlRepository;
import com.blaisecoder.urlshortener.services.IShortUrlService;
import com.blaisecoder.urlshortener.utils.RandomStringGenerator;
import com.blaisecoder.urlshortener.utils.ShortUrlMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShortUrlServiceImpl implements IShortUrlService {
    //    shorturl repository

    private  final ShortUrlRepository shortUrlRepository;
   private  final RandomStringGenerator randomStringGenerator;

    @Override
    public ShortUrl generateShortUrl(GenerateShortUrlDTO dto) throws DuplicateRecordException {
        ShortUrl url= ShortUrlMapper.toEntity(dto);
        String shortUrl=randomStringGenerator.generateShortUrl();
        url.setShortUrl(shortUrl);
// check if the short url already exists
        if(this.shortUrlRepository.existsByCustomId(dto.getCustomId())){
            log.info(" creating  the  shorturl  with customIdId  {} and longUrl {} failed ",url.getCustomId(),url.getLongUrl());
            throw new DuplicateRecordException("ShortUrl","customId",dto.getCustomId());

        }

        if(dto.getCustomId()==null || dto.getCustomId().equalsIgnoreCase("")){
            generateCustomId(url);
        }

        shortUrlRepository.save(url);
        return url;
    }

    private void generateCustomId(ShortUrl url) {

        boolean uniqueId=false;
        while (!uniqueId) {
            String customId = randomStringGenerator.generateRandomAlphanumericString(8);
            if (!shortUrlRepository.existsByCustomId(customId)) {
                url.setCustomId(customId);
                uniqueId=true;


//            if the customId Is not unique then it repeat itself again and again
            }
        }

    }

    @Override
    public String getLongUrl(String id) throws ResourceNotFoundException {
       ShortUrl shortUrl=shortUrlRepository.findByCustomId(id).orElseThrow(()->new ResourceNotFoundException("ShortUrl","customId",id));
        return shortUrl.getLongUrl();

    }

    @Override
    public void deleteShortUrl(String id) {
ShortUrl entity=this.shortUrlRepository.findByCustomId(id).orElseThrow();
this.shortUrlRepository.delete(entity);
log.info("Short url with customId {} is successfully deleted",id);

    }

}
