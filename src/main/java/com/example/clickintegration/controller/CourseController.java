package com.example.clickintegration.controller;

import com.example.clickintegration.dto.request.CourseRequestDto;
import com.example.clickintegration.exception.ApiResponse;
import com.example.clickintegration.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService service;

    @PostMapping("/create")
    public ApiResponse addCourse(@RequestBody CourseRequestDto dto) {
        return service.add(dto);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteCourse(@PathVariable Integer id) {
        return service.delete(id);
    }

    @PutMapping("/update/{id}")
    public ApiResponse updateCourse(
            @PathVariable Integer id,
            @RequestBody CourseRequestDto dto
    ) {
        return service.update(id, dto);
    }

    @GetMapping("/get-main")
    public ApiResponse getCourses() {
        return service.getList();
    }

    @GetMapping("/get/{id}")
    public ApiResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }
}
