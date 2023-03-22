package com.example.clickintegration.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class NewsDto {
    private String titleRu;
    private String titleUz;
    private String shortContentRu;
    private String shortContentUz;
    private String date;
    private String alias;
    private boolean status;
    private MultipartFile fileName;
}
