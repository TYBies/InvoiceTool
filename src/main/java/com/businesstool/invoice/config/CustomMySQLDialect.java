package com.businesstool.invoice.config;

import org.hibernate.dialect.MySQL55Dialect;

public class CustomMySQLDialect extends MySQL55Dialect {
    @Override
    public String getTableTypeString() {
        return "ENGINE=MyISAM";
    }
}
