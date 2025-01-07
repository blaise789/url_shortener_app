package com.blaisecoder.urlshortener.models.dtos;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;
@Data

public class GenerateShortUrlDTO {
    private String customId;
    @NotEmpty
    @URL
    private String longUrl;
    private LocalDateTime expiryTime;



}
