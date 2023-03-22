package com.example.clickintegration.dto.request;

import lombok.Data;

@Data
public class MenuDto {
    private String titleUz;
    private String titleRu;
    private String contentUz;
    private String contentRu;
    private String option;
    private String date;
    private String alias;
    private boolean status;
}
