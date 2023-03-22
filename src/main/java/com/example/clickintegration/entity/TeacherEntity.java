package com.example.clickintegration.entity;

import com.example.clickintegration.dto.request.TeacherRequestDto;
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
public class TeacherEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private String nameUz;
    private String nameRu;
    private String shortContentRu;
    private String shortContentUz;
    private boolean status;
    private String fileName;

    public static TeacherEntity of(TeacherRequestDto dto) {
        return TeacherEntity.builder()
                .nameRu(dto.getNameRu())
                .nameUz(dto.getNameUz())
                .shortContentUz(dto.getShortContentUz())
                .shortContentRu(dto.getShortContentRu())
                .status(dto.isStatus())
                .build();
    }
}
