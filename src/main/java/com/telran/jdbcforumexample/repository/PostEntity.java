package com.telran.jdbcforumexample.repository;

import java.sql.Timestamp;

public class PostEntity {
    int id;
    String content;
    Timestamp dateTime;
    String userName;

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public String getUserName() {
        return userName;
    }
}
