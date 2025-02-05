// package com.fdjloto.config;

// import org.hibernate.dialect.Dialect;
// import org.hibernate.dialect.identity.IdentityColumnSupport;
// import org.hibernate.dialect.identity.IdentityColumnSupportImpl;

// public class SQLiteDialect extends Dialect {
//     public SQLiteDialect() {
//         registerColumnType(java.sql.Types.INTEGER, "integer");
//         registerColumnType(java.sql.Types.VARCHAR, "text");
//         registerColumnType(java.sql.Types.BLOB, "blob");
//     }

//     @Override
//     public boolean hasAlterTable() {
//         return false;
//     }

//     @Override
//     public IdentityColumnSupport getIdentityColumnSupport() {
//         return new IdentityColumnSupportImpl();
//     }
// }

