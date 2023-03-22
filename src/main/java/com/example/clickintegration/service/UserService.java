package com.example.clickintegration.service;

import com.example.clickintegration.dto.request.UserLoginRequest;
import com.example.clickintegration.dto.request.UserRegisterDto;
import com.example.clickintegration.dto.response.UserLoginResponse;
import com.example.clickintegration.dto.response.UserResponseDto;
import com.example.clickintegration.entity.UserEntity;
import com.example.clickintegration.exception.ApiResponse;
import com.example.clickintegration.exception.RecordNotFound;
import com.example.clickintegration.jwt.JwtUtils;
import com.example.clickintegration.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final AuthenticationManager authenticationManager;

    public ApiResponse add(UserRegisterDto dto) {
        Optional<UserEntity> byUsername = repository.findByUsername(dto.getUsername());
        if (byUsername.isPresent()) {
            throw new RecordNotFound("this username already exist");
        }
        UserEntity of = UserEntity.of(dto);
        of.setPassword(passwordEncoder.encode(dto.getPassword()));
        UserEntity save = repository.save(of);
        UserResponseDto response = UserResponseDto.of(save);
        return ApiResponse.builder()
                .message(HttpStatus.OK.name())
                .data(response)
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    private UserEntity findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RecordNotFound("User not found"));
    }

    public ApiResponse update(UserRegisterDto dto) {
        Optional<UserEntity> byUsername = repository.findByUsername(dto.getUsername());
        UserEntity user = byUsername.orElseThrow(() -> new RecordNotFound("User not found"));
        if (dto.getName() != null && !dto.getName().equals(""))
            user.setName(dto.getName());
        if (dto.getPassword() != null && !dto.getPassword().equals(""))
            user.setPassword(dto.getPassword());
        if (dto.getUsername() != null && !dto.getUsername().equals(""))
            user.setUsername(dto.getUsername());
        if (dto.isEnabled() != user.isEnabled())
            user.setEnabled(dto.isEnabled());
        UserEntity save = repository.save(user);
        UserResponseDto response = UserResponseDto.of(save);
        return ApiResponse.builder()
                .message(HttpStatus.OK.name())
                .statusCode(HttpStatus.OK.value())
                .data(response)
                .build();
    }

    public ApiResponse delete(Integer id) {
        UserEntity byId = findById(id);
        repository.delete(byId);
        return ApiResponse.builder()
                .message(HttpStatus.OK.name())
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    public ApiResponse getList() {
        List<UserEntity> all = repository.findAll();
        List<UserResponseDto> collect = all.stream().map((response) ->
                UserResponseDto.of(response)).collect(Collectors.toList());
        return ApiResponse.builder()
                .message(HttpStatus.OK.name())
                .statusCode(HttpStatus.OK.value())
                .data(collect)
                .build();
    }

    public ApiResponse getById(Integer id) {
        UserEntity byId = findById(id);
        UserResponseDto of = UserResponseDto.of(byId);
        return ApiResponse.builder()
                .message(HttpStatus.OK.name())
                .statusCode(HttpStatus.OK.value())
                .data(of)
                .build();
    }

    private UserLoginResponse getJwtToken(UserEntity userDetails){
        return UserLoginResponse.builder()
                .token("Bearer " + JwtUtils.generateToken(userDetails))
                .build();
    }

    public UserLoginResponse login(UserLoginRequest userLoginRequest) {
        Authentication authentication
                = new UsernamePasswordAuthenticationToken(
                userLoginRequest.getUsername(),
                userLoginRequest.getPassword()
        );
        Authentication authenticate = authenticationManager.authenticate(authentication);
        try {
            return getJwtToken((UserEntity) authenticate.getPrincipal());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
