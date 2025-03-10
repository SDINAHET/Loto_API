package com.fdjloto.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "history")
public class History {

    @Id
    private String id;

    private String drawDate;
    private Map<Integer, Double> gains;

    public History() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDrawDate() {
        return drawDate;
    }

    public void setDrawDate(String drawDate) {
        this.drawDate = drawDate;
    }

    public Map<Integer, Double> getGains() {
        return gains;
    }

    public void setGains(Map<Integer, Double> gains) {
        this.gains = gains;
    }
}
