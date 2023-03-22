package com.example.clickintegration.service;

import com.example.clickintegration.dto.request.CourseRequestDto;
import com.example.clickintegration.dto.response.CourseResponseDto;
import com.example.clickintegration.entity.CourseEntity;
import com.example.clickintegration.exception.RecordNotFound;
import com.example.clickintegration.repository.CourseCategoryRepository;
import com.example.clickintegration.repository.CourseRepository;
import com.example.clickintegration.exception.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository repository;
    private final CourseCategoryRepository cRepository;
    private final ImageService imageService;

    public ApiResponse add(CourseRequestDto dto) {
        //String image = imageService.uploadImage(dto.getFileName());
        CourseEntity entity = getEntity(dto);
        CourseEntity save = repository.save(entity);
        return ApiResponse.builder()
                .data(CourseResponseDto.of(save))
                .message(HttpStatus.OK.name())
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    private CourseEntity findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RecordNotFound("Course not found"));
    }

    public ApiResponse delete(Integer id) {
        CourseEntity byId = findById(id);
        repository.delete(byId);
        return ApiResponse.builder()
                .message(HttpStatus.OK.name())
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    public ApiResponse update(Integer id, CourseRequestDto newCourse) {
        CourseEntity byId = findById(id);
        if (newCourse.getTitleUz() != null && !newCourse.getTitleUz().equals(""))
            byId.setTitleUz(newCourse.getTitleUz());
        if (newCourse.getTitleRu() != null && !newCourse.getTitleRu().equals(""))
            byId.setTitleRu(newCourse.getTitleRu());
        if (newCourse.getShortContentRu() != null && !newCourse.getShortContentRu().equals(""))
            byId.setShortContentRu(newCourse.getShortContentRu());
        if (newCourse.getShortContentUz() != null && !newCourse.getShortContentUz().equals(""))
            byId.setShortContentUz(newCourse.getShortContentUz());
        if (newCourse.getPrice() != null && !newCourse.getPrice().equals(""))
            byId.setPrice(newCourse.getPrice());
        if (newCourse.getData() != null && !newCourse.getData().equals(""))
            byId.setData(newCourse.getData());
        if (newCourse.getFileName() != null && !newCourse.getFileName().equals("")) {
            String image = imageService.updateImage(newCourse.getFileName(), byId.getFileName());
            byId.setFileName(image);
        }
        if (newCourse.getCategoryId() != null || !newCourse.getCategoryId().equals(""))
            byId.setCategory(cRepository.findById(newCourse.getCategoryId()).orElse(null));
        if (newCourse.getData() != null || !newCourse.getData().equals(""))
            byId.setData(newCourse.getData());
        if (newCourse.getAlias() != null || !newCourse.getAlias().equals(""))
            byId.setAlias(newCourse.getAlias());
        CourseEntity save = repository.save(byId);
        return ApiResponse.builder()
                .data(CourseResponseDto.of(save))
                .message(HttpStatus.OK.name())
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    public ApiResponse getList() {
        List<CourseEntity> all = repository.findAll();
        List<CourseResponseDto> collect = all.stream().map((res) ->
                CourseResponseDto.of(res)).collect(Collectors.toList());
        return ApiResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .data(collect)
                .build();
    }

    public ApiResponse getById(Integer id) {
        CourseEntity byId = findById(id);
        return ApiResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .data(CourseResponseDto.of(byId))
                .build();
    }

    private CourseEntity getEntity(CourseRequestDto dto) {
        return CourseEntity.builder()
                .titleRu(dto.getTitleRu())
                .titleUz(dto.getTitleUz())
                .shortContentRu(dto.getShortContentRu())
                .shortContentUz(dto.getShortContentUz())
                .price(dto.getPrice())
                .category(cRepository.findById(dto.getCategoryId()).orElse(null))
                .data(dto.getData())
                .alias(dto.getAlias())
                .fileName("")
                .status(dto.isStatus())
                .build();
    }
}
