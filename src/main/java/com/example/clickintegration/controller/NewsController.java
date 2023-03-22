package com.example.clickintegration.controller;

import com.example.clickintegration.dto.request.NewsDto;
import com.example.clickintegration.entity.NewsEntity;
import com.example.clickintegration.exception.ApiResponse;
import com.example.clickintegration.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/news")
public class NewsController {
    private final NewsService service;

    @PostMapping("/create")
    public ApiResponse addNews(@RequestBody NewsDto dto) {
        return service.add(dto);
    }

    @PutMapping("/update/{id}")
    public ApiResponse updateNews(
            @PathVariable Integer id,
            @RequestBody NewsDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteNews(@PathVariable Integer id) {
        return service.delete(id);
    }

    @GetMapping("/get-main")
    public ApiResponse getAllNews() {
        return service.getList();
    }

    @GetMapping("/get/{id}")
    public ApiResponse getByid(@PathVariable Integer id) {
        return service.getById(id);
    }
}
