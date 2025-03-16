package com.fdjloto.config;

import org.hibernate.community.dialect.SQLiteDialect;
import org.hibernate.dialect.DatabaseVersion;

public class CustomSQLiteDialect extends SQLiteDialect {

    public CustomSQLiteDialect() {
        super(DatabaseVersion.make(3));
    }
}
