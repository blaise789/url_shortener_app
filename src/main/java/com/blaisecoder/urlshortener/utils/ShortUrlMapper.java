package com.blaisecoder.urlshortener.utils;

import com.blaisecoder.urlshortener.models.ShortUrl;
import com.blaisecoder.urlshortener.models.dtos.GenerateShortUrlDTO;

public class ShortUrlMapper {
    public static ShortUrl toEntity(GenerateShortUrlDTO dto){
        ShortUrl url=new ShortUrl();
        url.setLongUrl(dto.getLongUrl());
        url.setExpiryTime(dto.getExpiryTime());
        return url;

    }
}
