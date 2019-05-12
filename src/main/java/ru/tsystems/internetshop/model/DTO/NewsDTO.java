package ru.tsystems.internetshop.model.DTO;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * This class is DTO for news entity
 */
@Data
public class NewsDTO {
    private long id;
    private String article;
    private String text;
    private LocalDateTime writingDate;
}
