package com.example.clickintegration.repository;

import com.example.clickintegration.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<MenuEntity,Integer> {
}
