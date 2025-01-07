package com.blaisecoder.urlshortener.repositories;

import com.blaisecoder.urlshortener.models.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortUrlRepository extends JpaRepository<ShortUrl,String> {
    public boolean existsByCustomId( String customId);
}
