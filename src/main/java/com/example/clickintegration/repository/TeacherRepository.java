package com.example.clickintegration.repository;

import com.example.clickintegration.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<TeacherEntity,Integer> {
}
