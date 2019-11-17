package com.wechatweb.demo.entity;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageTemplate {
    @JsonProperty("touser")
    private String toUser;

    @JsonProperty("template_id")
    private String templateId;

    private String url;

    private Map<String,String> miniprogram;

    private Map<String, Map<String, String>> data = new HashMap<>();

    public static Map<String, String> initData(String value, String color) {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("value", value);
        data.put("color", color);
        return data;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getMiniprogram() {
        return miniprogram;
    }

    public void setMiniprogram(Map<String, String> miniprogram) {
        this.miniprogram = miniprogram;
    }

    public Map<String, Map<String, String>> getData() {
        return data;
    }

    public void setData(Map<String, Map<String, String>> data) {
        this.data = data;
    }

}