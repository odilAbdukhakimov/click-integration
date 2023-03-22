package com.example.clickintegration.entity;

import com.example.clickintegration.dto.request.UserRegisterDto;
import com.example.clickintegration.entity.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@EntityListeners(AuditingEntityListener.class)
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @Column(unique = true)
    private String username;
    protected String password;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private List<RoleEnum> roles;
    private boolean isEnabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (roles != null) {
            return roles.stream().map((role) -> new SimpleGrantedAuthority("ROLE_" + role)).toList();
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public static UserEntity of(UserRegisterDto dto) {
        return UserEntity.builder()
                .roles(dto.getRoles())
                .isEnabled(dto.isEnabled())
                .username(dto.getUsername())
                .name(dto.getName())
                .build();
    }
}
