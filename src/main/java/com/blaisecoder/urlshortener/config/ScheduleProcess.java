package com.blaisecoder.urlshortener.config;

import com.blaisecoder.urlshortener.models.ShortUrl;
import com.blaisecoder.urlshortener.repositories.ShortUrlRepository;
import com.blaisecoder.urlshortener.services.IShortUrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class ScheduleProcess {
//    cron job checks expired urls every 10 mins and deletes  them
    private final ShortUrlRepository shortUrlRepository;
    @Scheduled(fixedDelay = 600000)
    public void deleteExpiredUrls(){
        log.info("Service to clean expired urls starts at{}", LocalDateTime.now());
        List<ShortUrl> expiredUrls=shortUrlRepository.findAllByExpireTimeBefore(LocalDateTime.now());
        log.info(expiredUrls.toString());
       if(!expiredUrls.isEmpty()){
           shortUrlRepository.deleteAll(expiredUrls);
           log.info("Deleted records {}",expiredUrls.size());
       }
    }
}
