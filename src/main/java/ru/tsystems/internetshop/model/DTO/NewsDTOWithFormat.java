package ru.tsystems.internetshop.model.DTO;

import lombok.Data;

/**
 * This class is DTO for news entity
 */
@Data
public class NewsDTOWithFormat {
    private long id;
    private String article;
    private String text;
    private String writingDate;
}
