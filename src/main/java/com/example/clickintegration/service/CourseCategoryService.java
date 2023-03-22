package com.example.clickintegration.service;

import com.example.clickintegration.dto.request.CourseCategoryRequestDto;
import com.example.clickintegration.dto.response.CourseCategoryResponseDto;
import com.example.clickintegration.entity.CourseCategoryEntity;
import com.example.clickintegration.exception.RecordNotFound;
import com.example.clickintegration.repository.CourseCategoryRepository;
import com.example.clickintegration.exception.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseCategoryService {
    private final CourseCategoryRepository repository;

    public ApiResponse add(CourseCategoryRequestDto dto) {
        CourseCategoryEntity of = CourseCategoryEntity.of(dto);
        CourseCategoryEntity save = repository.save(of);
        return ApiResponse.builder()
                .data(CourseCategoryResponseDto.of(save))
                .message(HttpStatus.OK.name())
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    private CourseCategoryEntity findById(Integer id) {
        return repository.findById(id).orElseThrow(() ->
                new RecordNotFound(String.format("The user for this %s id was not found", id)));
    }

    public ApiResponse delete(Integer id) {
        CourseCategoryEntity byId = findById(id);
        repository.delete(byId);
        return ApiResponse.builder()
                .message(HttpStatus.OK.name())
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    public ApiResponse update(Integer id, CourseCategoryRequestDto dto) {
        CourseCategoryEntity byId = findById(id);
        if (dto.getTitleRu() != null && !dto.getTitleRu().equals(""))
            byId.setTitleRu(dto.getTitleRu());
        if (dto.getTitleUz() != null && !dto.getTitleUz().equals(""))
            byId.setTitleUz(dto.getTitleUz());
        CourseCategoryEntity save = repository.save(byId);
        return ApiResponse.builder()
                .data(CourseCategoryResponseDto.of(save))
                .statusCode(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .build();
    }

    public ApiResponse getList() {
        List<CourseCategoryEntity> all = repository.findAll();
        List<CourseCategoryResponseDto> collect = all.stream().map((response) ->
                CourseCategoryResponseDto.of(response)).collect(Collectors.toList());
        return ApiResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .data(collect)
                .build();
    }

    public ApiResponse getById(Integer id) {
        CourseCategoryEntity byId = findById(id);
        return ApiResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .data(CourseCategoryResponseDto.of(byId))
                .build();
    }
}
