package com.example.clickintegration.dto.response;

import com.example.clickintegration.entity.CourseCategoryEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseCategoryResponseDto {
    private Integer id;
    @JsonProperty("title_ru")
    private String titleRu;
    @JsonProperty("title_uz")
    private String titleUz;
    public static CourseCategoryResponseDto of (CourseCategoryEntity entity){
        return CourseCategoryResponseDto.builder()
                .id(entity.getId())
                .titleRu(entity.getTitleRu())
                .titleUz(entity.getTitleUz())
                .build();
    }
}
