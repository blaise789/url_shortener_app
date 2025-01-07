package com.blaisecoder.urlshortener.repositories;

import com.blaisecoder.urlshortener.models.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShortUrlRepository extends JpaRepository<ShortUrl,String> {
    public boolean existsByCustomId( String customId);
    public Optional<ShortUrl> findByCustomId(String customId);
    public List<ShortUrl> findAllByExpireTimeBefore(LocalDateTime time);
}

