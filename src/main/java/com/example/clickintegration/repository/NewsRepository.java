package com.example.clickintegration.repository;

import com.example.clickintegration.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<NewsEntity,Integer> {
}
