package com.example.clickintegration.entity;

import com.example.clickintegration.dto.request.CourseRequestDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class CourseEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private String titleRu;
    private String titleUz;
    private String shortContentRu;
    private String shortContentUz;
    private Double price;
    @ManyToOne
    private CourseCategoryEntity category;
    private String data;
    private String alias;
    private boolean status;
    private String fileName;

}
