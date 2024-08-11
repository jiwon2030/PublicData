package com.example.jiwon.Repository;

import com.example.jiwon.Entity.ContentItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentItemRepository extends JpaRepository<ContentItem, Long> {
}
