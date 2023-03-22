package com.example.clickintegration.controller;

import com.example.clickintegration.dto.request.MenuDto;
import com.example.clickintegration.exception.ApiResponse;
import com.example.clickintegration.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menu")
public class MenuController {
    private final MenuService service;

    @PostMapping("/create")
    public ApiResponse addMenu(@RequestBody MenuDto dto) {
        return service.add(dto);
    }

    @PutMapping("/update/{id}")
    public ApiResponse updateMenu(
            @PathVariable Integer id,
            @RequestBody MenuDto dto
    ) {
        return service.update(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMenu(@PathVariable Integer id) {
        service.delete(id);
    }

    @GetMapping("/get-main")
    public ApiResponse getAll() {
        return service.getList();
    }

    @GetMapping("/get/{id}")
    public ApiResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }
}
