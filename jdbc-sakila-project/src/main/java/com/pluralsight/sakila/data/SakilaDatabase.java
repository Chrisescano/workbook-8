package com.pluralsight.sakila.data;

import org.apache.commons.dbcp2.BasicDataSource;

public class SakilaDatabase {
    private final BasicDataSource dataSource;

    public SakilaDatabase() {
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/sakila");
        dataSource.setUsername("superuser");
        dataSource.setPassword("superuser");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
    }

    public BasicDataSource getDataSource() {
        return dataSource;
    }
}
