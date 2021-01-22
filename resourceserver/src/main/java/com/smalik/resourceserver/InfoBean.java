package com.smalik.resourceserver;

import java.util.UUID;

public class InfoBean {

    private String name;
    private String id;

    public InfoBean(String name) {
        this.name = name;
        this.id = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
