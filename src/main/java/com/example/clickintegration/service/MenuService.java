package com.example.clickintegration.service;

import com.example.clickintegration.dto.request.MenuDto;
import com.example.clickintegration.dto.response.MenuResponseDto;
import com.example.clickintegration.entity.MenuEntity;
import com.example.clickintegration.exception.ApiResponse;
import com.example.clickintegration.exception.RecordNotFound;
import com.example.clickintegration.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository repository;

    public ApiResponse add(MenuDto dto) {
        MenuEntity of = MenuEntity.of(dto);
        MenuEntity save = repository.save(of);
        MenuResponseDto res = MenuResponseDto.of(save);
        return ApiResponse.builder()
                .message(HttpStatus.OK.name())
                .data(res)
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    private MenuEntity findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RecordNotFound("this menu note found"));
    }

    public ApiResponse delete(Integer id) {
        MenuEntity byId = findById(id);
        repository.delete(byId);
        return new ApiResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), null);
    }

    public ApiResponse update(Integer id, MenuDto dto) {
        MenuEntity byId = findById(id);
        if (dto.getTitleUz() != null && !dto.getTitleUz().equals(""))
            byId.setTitleUz(dto.getTitleUz());
        if (dto.getTitleRu() != null && !dto.getTitleRu().equals(""))
            byId.setTitleRu(dto.getTitleRu());
        if (dto.getContentUz() != null && !dto.getContentUz().equals(""))
            byId.setContentUz(dto.getContentUz());
        if (dto.getContentRu() != null && !dto.getContentRu().equals(""))
            byId.setContentRu(dto.getContentRu());
        if (dto.getAlias() != null && !dto.getAlias().equals(""))
            byId.setAlias(dto.getAlias());
        if (dto.getOption() != null && !dto.getOption().equals(""))
            byId.setOption(dto.getOption());
        if (dto.getDate() != null && !dto.getDate().equals(""))
            byId.setDate(dto.getDate());
        MenuEntity save = repository.save(byId);
        return ApiResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .data(MenuResponseDto.of(save))
                .build();
    }

    public ApiResponse getList() {
        List<MenuEntity> all = repository.findAll();
        List<MenuResponseDto> collect = all.stream().map((res) ->
                MenuResponseDto.of(res)).collect(Collectors.toList());
        return ApiResponse.builder()
                .message(HttpStatus.OK.name())
                .statusCode(HttpStatus.OK.value())
                .data(collect)
                .build();
    }

    public ApiResponse getById(Integer id) {
        MenuEntity byId = findById(id);
        return ApiResponse.builder()
                .message(HttpStatus.OK.name())
                .statusCode(HttpStatus.OK.value())
                .data(MenuResponseDto.of(byId))
                .build();
    }
}
