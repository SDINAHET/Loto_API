package com.fdjloto.config;

import org.hibernate.dialect.identity.IdentityColumnSupportImpl;

public class SQLiteIdentityColumnSupport extends IdentityColumnSupportImpl {

    @Override
    public String getIdentityColumnString(int type) {
        // SQLite uses "integer primary key autoincrement" for identity columns
        return "integer primary key autoincrement";
    }

    @Override
    public boolean supportsIdentityColumns() {
        return true;
    }
}
