package com.example.clickintegration.service;

import com.example.clickintegration.dto.request.NewsDto;
import com.example.clickintegration.entity.NewsEntity;
import com.example.clickintegration.exception.ApiResponse;
import com.example.clickintegration.exception.RecordNotFound;
import com.example.clickintegration.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepository repository;

    public ApiResponse add(NewsDto dto) {
        NewsEntity of = NewsEntity.of(dto);
        NewsEntity save = repository.save(of);
        return ApiResponse.builder()
                .message(HttpStatus.OK.name())
                .statusCode(HttpStatus.OK.value())
                .data(save)
                .build();
    }

    private NewsEntity findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RecordNotFound("this menu note found"));
    }

    public ApiResponse delete(Integer id) {
        NewsEntity byId = findById(id);
        repository.delete(byId);
        return ApiResponse.builder()
                .message(HttpStatus.OK.name())
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    public ApiResponse update(Integer id, NewsDto dto) {
        NewsEntity byId = findById(id);
        if (dto.getTitleRu() != null && !dto.getTitleRu().equals(""))
            byId.setTitleRu(dto.getTitleRu());
        if (dto.getTitleUz() != null && !dto.getTitleUz().equals(""))
            byId.setTitleUz(dto.getTitleUz());
        if (dto.getShortContentRu() != null && !dto.getShortContentRu().equals(""))
            byId.setShortContentRu(dto.getShortContentRu());
        if (dto.getShortContentUz() != null && !dto.getShortContentUz().equals(""))
            byId.setShortContentUz(dto.getShortContentUz());
        if (dto.getDate() != null && !dto.getDate().equals(""))
            byId.setDate(dto.getDate());
        if (dto.getAlias() != null && !dto.getAlias().equals(""))
            byId.setAlias(dto.getAlias());
        NewsEntity save = repository.save(byId);
        return ApiResponse.builder()
                .message(HttpStatus.OK.name())
                .statusCode(HttpStatus.OK.value())
                .data(save)
                .build();
    }

    public ApiResponse getList() {
        List<NewsEntity> all = repository.findAll();
        return ApiResponse.builder()
                .message(HttpStatus.OK.name())
                .statusCode(HttpStatus.OK.value())
                .data(all)
                .build();
    }
    public ApiResponse getById(Integer id){
        return ApiResponse.builder()
                .message(HttpStatus.OK.name())
                .statusCode(HttpStatus.OK.value())
                .data(findById(id))
                .build();
    }
}
