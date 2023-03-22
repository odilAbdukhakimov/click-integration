package com.example.clickintegration.repository;


import com.example.clickintegration.entity.AttachmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttachmentRepository extends JpaRepository<AttachmentEntity,Integer> {
    Optional<AttachmentEntity> findByName(String name);
}
