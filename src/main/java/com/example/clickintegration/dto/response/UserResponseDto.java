package com.example.clickintegration.dto.response;

import com.example.clickintegration.entity.UserEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {
    private Integer id;
    private String name;
    private String username;
    private String role;

    public static UserResponseDto of(UserEntity entity) {
        return UserResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .username(entity.getUsername())
                .role(entity.getRoles().get(0).name())
                .build();
    }
}
