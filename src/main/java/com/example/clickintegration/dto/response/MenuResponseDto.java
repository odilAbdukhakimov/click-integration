package com.example.clickintegration.dto.response;

import com.example.clickintegration.entity.MenuEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MenuResponseDto {
    private Integer id;
    private String titleUz;
    private String titleRu;
    private String contentUz;
    private String contentRu;
    private String option;
    private String date;
    private String alias;
    private boolean status;

    public static MenuResponseDto of(MenuEntity entity) {
        return MenuResponseDto.builder()
                .id(entity.getId())
                .titleUz(entity.getTitleUz())
                .titleRu(entity.getTitleRu())
                .contentRu(entity.getContentRu())
                .contentUz(entity.getContentUz())
                .option(entity.getOption())
                .date(entity.getDate())
                .alias(entity.getAlias())
                .status(entity.isStatus())
                .build();
    }
}
