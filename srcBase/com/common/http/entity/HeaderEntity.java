package com.common.http.entity;

import java.util.HashMap;


public class HeaderEntity extends HashMap<String, String> {

    private static final long serialVersionUID = 1L;

    public HeaderEntity() {
        put ("Content-Type", "application/json");
    }

}