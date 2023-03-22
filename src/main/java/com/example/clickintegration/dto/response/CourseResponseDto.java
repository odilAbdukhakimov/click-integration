package com.example.clickintegration.dto.response;

import com.example.clickintegration.entity.CourseCategoryEntity;
import com.example.clickintegration.entity.CourseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseResponseDto {
    private Integer id;
    @JsonProperty("title_ru")
    private String titleRu;
    @JsonProperty("title_uz")
    private String titleUz;
    @JsonProperty("short_content_ru")
    private String shortContentRu;
    @JsonProperty("short_content_uz")
    private String shortContentUz;
    private Double price;
    @JsonProperty("category_id")
    private int categoryId;
    private String data;
    private String alias;
    public static CourseResponseDto of (CourseEntity entity){
        return CourseResponseDto.builder()
                .id(entity.getId())
                .titleUz(entity.getTitleUz())
                .titleRu(entity.getTitleRu())
                .shortContentRu(entity.getShortContentRu())
                .shortContentUz(entity.getShortContentUz())
                .price(entity.getPrice())
                .data(entity.getData())
                .alias(entity.getAlias())
                .build();
    }
}
