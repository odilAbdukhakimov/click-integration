package com.example.clickintegration.entity;

import com.example.clickintegration.dto.request.CourseCategoryRequestDto;
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
public class CourseCategoryEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private String titleRu;
    private String titleUz;
    private boolean status;
    public static CourseCategoryEntity of (CourseCategoryRequestDto dto){
        return CourseCategoryEntity.builder()
                .status(dto.isStatus())
                .titleRu(dto.getTitleRu())
                .titleUz(dto.getTitleUz())
                .build();
    }
}
