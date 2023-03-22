package com.example.clickintegration.entity;

import com.example.clickintegration.dto.request.MenuDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class MenuEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private String titleUz;
    private String titleRu;
    private String contentUz;
    private String contentRu;
    private String option;
    private String date;
    private String alias;
    private boolean status;

    public static MenuEntity of (MenuDto dto){
        return MenuEntity.builder()
                .titleUz(dto.getTitleUz())
                .titleRu(dto.getTitleRu())
                .contentRu(dto.getContentRu())
                .contentUz(dto.getContentUz())
                .option(dto.getOption())
                .date(dto.getDate())
                .alias(dto.getAlias())
                .status(dto.isStatus())
                .build();
    }
}
