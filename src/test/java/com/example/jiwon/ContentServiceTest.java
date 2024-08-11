package com.example.jiwon;

import com.example.jiwon.Entity.ContentItem;
import com.example.jiwon.Repository.ContentItemRepository;
import com.example.jiwon.Service.ContentService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc
public class ContentServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContentItemRepository contentItemRepository;

    @Autowired
    private ContentService contentService;

    @BeforeEach
    public void setSup() {
        contentItemRepository.deleteAll();
    }

    @Test
    public void testFetchAndSaveContentItems() throws Exception {
        contentService.fetchAndSaveContentItems(1, 50, null);

        List<ContentItem> contentItems = contentItemRepository.findAll();
        assertFalse(contentItems.isEmpty(), "Content items should be saved in the database");

        assertEquals(50, contentItems.size(), "Should have saved 50 content items");
    }
}
