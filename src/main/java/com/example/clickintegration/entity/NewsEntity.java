package com.example.clickintegration.entity;

import com.example.clickintegration.dto.request.NewsDto;
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
public class NewsEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private String titleRu;
    private String titleUz;
    private String shortContentRu;
    private String shortContentUz;
    private String date;
    private String alias;
    private String fileName;
    private boolean status;
    public static NewsEntity of (NewsDto dto){
        return NewsEntity.builder()
                .titleRu(dto.getTitleRu())
                .titleUz(dto.getTitleUz())
                .shortContentRu(dto.getShortContentRu())
                .shortContentUz(dto.getShortContentUz())
                .date(dto.getDate())
                .alias(dto.getAlias())
                .status(dto.isStatus())
                .build();
    }
}
