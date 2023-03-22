package com.example.clickintegration.dto.request;

import com.example.clickintegration.entity.CourseCategoryEntity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CourseRequestDto {
    private String titleRu;
    private String titleUz;
    private String shortContentRu;
    private String shortContentUz;
    private Double price;
    private Integer categoryId;
    private String data;
    private String alias;
    private boolean status;
    private MultipartFile fileName;
}
