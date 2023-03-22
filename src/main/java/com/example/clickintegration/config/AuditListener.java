package com.example.clickintegration.config;

import com.example.clickintegration.entity.UserEntity;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditListener implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()){
            UserEntity userEntity=(UserEntity) authentication.getPrincipal();
            return Optional.of(userEntity.getUsername());
        }
        return Optional.empty();
    }
}
