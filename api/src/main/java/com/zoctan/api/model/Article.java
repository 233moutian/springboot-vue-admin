package com.zoctan.api.model;

/**
 * Created by zxw on 2018/7/26 0026.
 */
public class Article {
    private Integer id;           //: '@increment',
    private String timestamp;          //: +Mock.Random.date('T'),
    private String author;          //: '@first',
    private String reviewer;          //: '@first',
    private String title;          //: '@title(5, 10)',
    private String content_short;          //: '我是测试数据',
    private String content;          //: baseContent,
    private Float forecast;          //: '@float(0, 100, 2, 2)',
    //    private   importance;          //
    private String display_time;          //: '@datetime',
    private Boolean comment_disabled;          //: true,
    private Integer pageviews;          //: '@integer(300, 5000)',
    private String image_uri;
//    private   platforms;          //: ['a-platform']

    public Article() {
    }

    public Article(Integer id, String timestamp, String author, String reviewer, String title, String content_short, String content, Float forecast, String display_time, Boolean comment_disabled, Integer pageviews, String image_uri) {
        this.id = id;
        this.timestamp = timestamp;
        this.author = author;
        this.reviewer = reviewer;
        this.title = title;
        this.content_short = content_short;
        this.content = content;
        this.forecast = forecast;
        this.display_time = display_time;
        this.comment_disabled = comment_disabled;
        this.pageviews = pageviews;
        this.image_uri = image_uri;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent_short() {
        return content_short;
    }

    public void setContent_short(String content_short) {
        this.content_short = content_short;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Float getForecast() {
        return forecast;
    }

    public void setForecast(Float forecast) {
        this.forecast = forecast;
    }

    public String getDisplay_time() {
        return display_time;
    }

    public void setDisplay_time(String display_time) {
        this.display_time = display_time;
    }

    public Boolean getComment_disabled() {
        return comment_disabled;
    }

    public void setComment_disabled(Boolean comment_disabled) {
        this.comment_disabled = comment_disabled;
    }

    public Integer getPageviews() {
        return pageviews;
    }

    public void setPageviews(Integer pageviews) {
        this.pageviews = pageviews;
    }

    public String getImage_uri() {
        return image_uri;
    }

    public void setImage_uri(String image_uri) {
        this.image_uri = image_uri;
    }
}
