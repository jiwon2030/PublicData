package com.example.jiwon;

import com.example.jiwon.Controller.ContentController;
import com.example.jiwon.Entity.ContentItem;
import com.example.jiwon.Repository.ContentItemRepository;
import com.example.jiwon.Service.ContentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ContentController.class)
public class ContentControllerTest {

    private MockMvc mockMvc;
    private ContentItemRepository contentItemRepository;
    @MockBean
    private ContentService contentService;

    @BeforeEach
    public void setUp() {
        contentItemRepository.deleteAll();
    }

    @Test
    public void testFetchAndSaveContentItemsEndpoint() throws Exception {
        ContentItem mockItem1 = new ContentItem();
        mockItem1.setConUid(1L);
        mockItem1.setConTitle("Test Title 1");

        ContentItem mockItem2 = new ContentItem();
        mockItem2.setConUid(2L);
        mockItem2.setConTitle("Test Title 2");

        List<ContentItem> mockContentItems = Arrays.asList(mockItem1, mockItem2);

        Mockito.when(contentService.fetchAndSaveContentItems(1, 50, null))
            .thenReturn(mockContentItems);

        mockMvc.perform(get("/api/fetchContentItems")
                .param("pageNo", "1")
                .param("numOfRows", "50"))
            .andExpect(status().isOk())
            .andExpect(content().json("[{\"conUid\":1,\"conTitle\":\"Test Title 1\"},{\"conUid\":2,\"conTitle\":\"Test Title 2\"}]"));

        List<ContentItem> contentItems = contentItemRepository.findAll();
        assertFalse(contentItems.isEmpty(), "Content items should be saved in the database");
        assertEquals(2, contentItems.size(), "Should have saved 2 content items (mocked data)");
    }
}
