package com.example.clickintegration.dto.response;

import com.example.clickintegration.entity.TeacherEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeacherResponseDto {
    private Integer id;
    @JsonProperty("name_uz")
    private String nameUz;
    @JsonProperty("name_ru")
    private String nameRu;
    @JsonProperty("short_content_ru")
    private String shortContentRu;
    @JsonProperty("short_content_uz")
    private String shortContentUz;

    public static TeacherResponseDto of(TeacherEntity entity) {
        return TeacherResponseDto.builder()
                .id(entity.getId())
                .nameUz(entity.getNameUz())
                .nameRu(entity.getNameRu())
                .shortContentUz(entity.getShortContentUz())
                .shortContentRu(entity.getShortContentRu())
                .build();
    }
}
