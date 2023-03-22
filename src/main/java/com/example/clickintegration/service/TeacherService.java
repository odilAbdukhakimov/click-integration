package com.example.clickintegration.service;

import com.example.clickintegration.dto.request.TeacherRequestDto;
import com.example.clickintegration.dto.response.TeacherResponseDto;
import com.example.clickintegration.entity.TeacherEntity;
import com.example.clickintegration.exception.RecordNotFound;
import com.example.clickintegration.repository.TeacherRepository;
import com.example.clickintegration.exception.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository repository;

    public ApiResponse add(TeacherRequestDto dto) {
        TeacherEntity of = TeacherEntity.of(dto);
        TeacherEntity save = repository.save(of);
        return ApiResponse.builder()
                .data(TeacherResponseDto.of(save))
                .message(HttpStatus.OK.getReasonPhrase())
                .statusCode(200)
                .build();
    }

    private TeacherEntity findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RecordNotFound("The teacher not found"));
    }

    public ApiResponse delete(Integer id) {
        TeacherEntity byId = findById(id);
        repository.delete(byId);
        return ApiResponse.builder()
                .message("successfully deleted")
                .statusCode(200)
                .build();
    }

    public ApiResponse update(Integer id, TeacherRequestDto dto) {
        TeacherEntity byId = findById(id);
        if (dto.getNameUz() != null && dto.getNameUz().equals(""))
            byId.setNameUz(dto.getNameUz());
        if (dto.getNameRu() != null && dto.getNameRu().equals(""))
            byId.setNameRu(dto.getNameRu());
        if (dto.getShortContentRu() != null && dto.getShortContentRu().equals(""))
            byId.setShortContentRu(dto.getShortContentRu());
        if (dto.getShortContentUz() != null && dto.getShortContentUz().equals(""))
            byId.setShortContentUz(dto.getShortContentUz());
        TeacherEntity save = repository.save(byId);
        return ApiResponse.builder()
                .data(TeacherResponseDto.of(save))
                .message("successfully updated")
                .statusCode(200)
                .build();
    }

    public ApiResponse getList() {
        return ApiResponse.builder()
                .statusCode(200)
                .message("get list")
                .data(repository.findAll())
                .build();
    }

    public ApiResponse getById(Integer id) {
        TeacherEntity byId = findById(id);
        return ApiResponse.builder()
                .statusCode(200)
                .message("get list")
                .data(TeacherResponseDto.of(byId))
                .build();
    }
}
