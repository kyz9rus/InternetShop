package ru.tsystems.internetshop.model.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NewsDTO {
    private long id;
    private String article;
    private String text;
    private LocalDate writingDate;
}
