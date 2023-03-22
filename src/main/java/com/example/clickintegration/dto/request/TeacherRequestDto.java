package com.example.clickintegration.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class TeacherRequestDto {
    private String nameUz;
    private String nameRu;
    private String shortContentRu;
    private String shortContentUz;
    private MultipartFile fileName;
    private boolean status;

}
