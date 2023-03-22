package com.example.clickintegration.controller;

import com.example.clickintegration.dto.request.TeacherRequestDto;
import com.example.clickintegration.exception.ApiResponse;
import com.example.clickintegration.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teachers")
public class TeacherController {
    private final TeacherService service;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ApiResponse addTeacher(@RequestBody TeacherRequestDto dto) {
        return service.add(dto);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteTeacher(@PathVariable Integer id) {
        return service.delete(id);
    }

    @PutMapping("/update/{id}")
    public ApiResponse updateTeacher(
            @PathVariable Integer id,
            @RequestBody TeacherRequestDto dto
    ) {
        return service.update(id, dto);
    }

    @GetMapping("/get-main")
    public ApiResponse getAllTeacher() {
        return service.getList();
    }

    @GetMapping("/get/{id}")
    public ApiResponse getByid(@PathVariable Integer id) {
        return service.getById(id);
    }
}
