package com.example.jiwon.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ContentItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long conUid;
    private String conDate;
    private String conSubtitle;
    private String conTitle;
    private String conHomepage;
    private String conInfo;
    private String conAddress;
    private Double conLatitude;
    private Double conLongitude;
    private String conSummary;
    private String conStartdate;
    private String conEnddate;
    private String conSponsor;
    private String conLocation;
    private String conTime;
    private String conParking;
    private String conContent;
    private String conPhone;
    private String conHost;
    private String conTransport;
    private Integer conIsenabled;
    private Integer conReadcnt;
    private String conSupervise;
    private String conReservelink;
    private String conAge;
    private String conPrice;
    private String conEtc;
    private Integer conRecommend;
    private Integer conPopular;
    private Integer conCourse;
    private String conMdfyid;
    private String conMdfydatetime;
    private String conImgfilename;
    private String srcTitle;
    private String conTicketstart;
    private String conTicketend;
    private Long codeUid;
    private String codeName;
    private Long mnuUid;
    private String mnuName;
    private String codePath;
    private Integer conLikecnt;
    private Integer conScrapcnt;
    private String conType;
    private Long topCode;
    private String menuPath;
    private String linkurl;
    private String conKeywords;

}
