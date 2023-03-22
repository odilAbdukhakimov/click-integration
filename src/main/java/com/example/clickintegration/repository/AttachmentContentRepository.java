package com.example.clickintegration.repository;


import com.example.clickintegration.entity.AttachmentContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContentEntity,Integer> {

    Optional<AttachmentContentEntity> findByAttachmentId(Integer attachment_id);
}
