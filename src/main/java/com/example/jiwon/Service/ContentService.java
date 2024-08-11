package com.example.jiwon.Service;

import com.example.jiwon.Entity.ContentItem;
import com.example.jiwon.Repository.ContentItemRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ContentItemRepository contentItemRepository;

    @Value("${openAPI.key}")
    private String defaultApiKey;

    public List<ContentItem> fetchAndSaveContentItems(int pageNo, int numOfRows, String serviceKey) {
        try {
            String apiKey = (serviceKey != null && !serviceKey.isEmpty()) ? serviceKey : defaultApiKey;

            String url = UriComponentsBuilder.fromHttpUrl("https://apis.data.go.kr/5050000/dwtwTrrstrService/getDwtwTrrstr")
                .queryParam("serviceKey", apiKey)
                .queryParam("numOfRows", numOfRows)
                .queryParam("pageNo", pageNo)
                .toUriString();

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "application/json");

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            String jsonResponse = response.getBody();

            return saveContentItemsFromJson(jsonResponse);

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private List<ContentItem> saveContentItemsFromJson(String jsonString) {
        JSONParser parser = new JSONParser();

        try {
            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
            JSONObject responseBody = (JSONObject) jsonObject.get("response");
            JSONObject body = (JSONObject) responseBody.get("body");
            JSONArray items = (JSONArray) ((JSONObject) body.get("items")).get("item");

            List<ContentItem> contentItemList = new ArrayList<>();

            for (Object obj : items) {
                JSONObject item = (JSONObject) obj;

                ContentItem contentItem = new ContentItem();

                contentItem.setConUid(Long.parseLong(item.get("CON_UID").toString()));
                contentItem.setConDate(item.get("CON_DATE") != null ? item.get("CON_DATE").toString() : null);
                contentItem.setConSubtitle(item.get("CON_SUBTITLE") != null ? item.get("CON_SUBTITLE").toString() : null);
                contentItem.setConTitle(item.get("CON_TITLE").toString());
                contentItem.setConHomepage(item.get("CON_HOMEPAGE") != null ? item.get("CON_HOMEPAGE").toString() : null);
                contentItem.setConInfo(item.get("CON_INFO") != null ? item.get("CON_INFO").toString() : null);
                contentItem.setConAddress(item.get("CON_ADDRESS") != null ? item.get("CON_ADDRESS").toString() : null);
                contentItem.setConLatitude(item.get("CON_LATITUDE") != null ? Double.parseDouble(item.get("CON_LATITUDE").toString()) : null);
                contentItem.setConLongitude(item.get("CON_LONGITUDE") != null ? Double.parseDouble(item.get("CON_LONGITUDE").toString()) : null);
                contentItem.setConSummary(item.get("CON_SUMMARY") != null ? item.get("CON_SUMMARY").toString() : null);
                contentItem.setConStartdate(item.get("CON_STARTDATE") != null ? item.get("CON_STARTDATE").toString() : null);
                contentItem.setConEnddate(item.get("CON_ENDDATE") != null ? item.get("CON_ENDDATE").toString() : null);
                contentItem.setConSponsor(item.get("CON_SPONSOR") != null ? item.get("CON_SPONSOR").toString() : null);
                contentItem.setConLocation(item.get("CON_LOCATION").toString());
                contentItem.setConTime(item.get("CON_TIME") != null ? item.get("CON_TIME").toString() : null);
                contentItem.setConParking(item.get("CON_PARKING") != null ? item.get("CON_PARKING").toString() : null);
                contentItem.setConContent(item.get("CON_CONTENT").toString());
                contentItem.setConPhone(item.get("CON_PHONE") != null ? item.get("CON_PHONE").toString() : null);
                contentItem.setConHost(item.get("CON_HOST") != null ? item.get("CON_HOST").toString() : null);
                contentItem.setConTransport(item.get("CON_TRANSPORT") != null ? item.get("CON_TRANSPORT").toString() : null);
                contentItem.setConIsenabled(Integer.parseInt(item.get("CON_ISENABLED").toString()));
                contentItem.setConReadcnt(Integer.parseInt(item.get("CON_READCNT").toString()));
                contentItem.setConSupervise(item.get("CON_SUPERVISE") != null ? item.get("CON_SUPERVISE").toString() : null);
                contentItem.setConReservelink(item.get("CON_RESERVELINK") != null ? item.get("CON_RESERVELINK").toString() : null);
                contentItem.setConAge(item.get("CON_AGE") != null ? item.get("CON_AGE").toString() : null);
                contentItem.setConPrice(item.get("CON_PRICE") != null ? item.get("CON_PRICE").toString() : null);
                contentItem.setConEtc(item.get("CON_ETC") != null ? item.get("CON_ETC").toString() : null);
                contentItem.setConRecommend(Integer.parseInt(item.get("CON_RECOMMEND").toString()));
                contentItem.setConPopular(Integer.parseInt(item.get("CON_POPULAR").toString()));
                contentItem.setConCourse(Integer.parseInt(item.get("CON_COURSE").toString()));
                contentItem.setConMdfyid(item.get("CON_MDFYID").toString());
                contentItem.setConMdfydatetime(item.get("CON_MDFYDATETIME").toString()); // Store as String
                contentItem.setConImgfilename(item.get("CON_IMGFILENAME") != null ? item.get("CON_IMGFILENAME").toString() : null);
                contentItem.setSrcTitle(item.get("SRC_TITLE") != null ? item.get("SRC_TITLE").toString() : null);
                contentItem.setConTicketstart(item.get("CON_TICKETSTART") != null ? item.get("CON_TICKETSTART").toString() : null);
                contentItem.setConTicketend(item.get("CON_TICKETEND") != null ? item.get("CON_TICKETEND").toString() : null);
                contentItem.setCodeUid(Long.parseLong(item.get("CODE_UID").toString()));
                contentItem.setCodeName(item.get("CODE_NAME").toString());
                contentItem.setMnuUid(Long.parseLong(item.get("MNU_UID").toString()));
                contentItem.setMnuName(item.get("MNU_NAME").toString());
                contentItem.setCodePath(item.get("CODE_PATH").toString());
                contentItem.setConLikecnt(Integer.parseInt(item.get("CON_LIKECNT").toString()));
                contentItem.setConScrapcnt(Integer.parseInt(item.get("CON_SCRAPCNT").toString()));
                contentItem.setConType(item.get("CON_TYPE").toString());
                contentItem.setTopCode(Long.parseLong(item.get("TOP_CODE").toString()));
                contentItem.setMenuPath(item.get("MENU_PATH").toString());
                contentItem.setLinkurl(item.get("LINKURL").toString());
                contentItem.setConKeywords(item.get("CON_KEYWORDS").toString());

                contentItemList.add(contentItem);
            }

            contentItemRepository.saveAll(contentItemList);
            return contentItemList;

        } catch (ParseException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}

