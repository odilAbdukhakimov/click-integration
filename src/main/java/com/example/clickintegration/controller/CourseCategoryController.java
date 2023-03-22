package com.example.clickintegration.controller;

import com.example.clickintegration.dto.request.CourseCategoryRequestDto;
import com.example.clickintegration.exception.ApiResponse;
import com.example.clickintegration.service.CourseCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses_category")
@RequiredArgsConstructor
public class CourseCategoryController {
    private final CourseCategoryService service;

    @PostMapping("/create")
    public ApiResponse addCategory(@RequestBody CourseCategoryRequestDto dto) {
        return service.add(dto);
    }

    @GetMapping("/get-main")
    public ApiResponse getCategories() {
        return service.getList();
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteCategory(@PathVariable Integer id) {
        return service.delete(id);
    }

    @PutMapping("/update/{id}")
    public ApiResponse updateCategory(
            @PathVariable Integer id,
            @RequestBody CourseCategoryRequestDto dto) {
        return service.update(id, dto);
    }

    @GetMapping("/get/{id}")
    public ApiResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }
}

