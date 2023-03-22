package com.example.clickintegration.dto.request;

import com.example.clickintegration.entity.enums.RoleEnum;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.List;

@Data
public class UserRegisterDto {
    private String name;
    @Column(unique = true)
    private String username;
    protected String password;
    private List<RoleEnum> roles;
    private boolean isEnabled=true;
}
