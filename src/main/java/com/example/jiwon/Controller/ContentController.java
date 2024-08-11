package com.example.jiwon.Controller;

import com.example.jiwon.Entity.ContentItem;
import com.example.jiwon.Service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ContentController {

    private final ContentService contentService;

    @GetMapping(value = "/fetchContentItems", produces = "application/json")
    public ResponseEntity<List<ContentItem>> fetchAndSaveContentItems(
                @RequestParam(required = false, defaultValue = "1") int pageNo,
                @RequestParam(required = false, defaultValue = "50") int numOfRows,
                @RequestParam(required = false) String serviceKey) {

        List<ContentItem> contentItems = contentService.fetchAndSaveContentItems(pageNo, numOfRows, serviceKey);
        return ResponseEntity.ok(contentItems);
    }
}
