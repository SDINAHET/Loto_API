package com.fdjloto.config;

import org.hibernate.boot.model.FunctionContributions;
import org.hibernate.boot.model.TypeContributions;
import org.hibernate.boot.spi.MetadataBuildingContext;
import org.hibernate.dialect.DatabaseVersion;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.engine.jdbc.Size;
import org.hibernate.query.sqm.function.SqmFunctionRegistry;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;

public class CustomSQLiteDialect extends Dialect {

    public CustomSQLiteDialect() {
        super(DatabaseVersion.make(3, 32));
    }

    @Override
    public void initializeFunctionRegistry(FunctionContributions functionContributions) {
        SqmFunctionRegistry functionRegistry = functionContributions.getFunctionRegistry();

        // Enregistrer les fonctions SQL communes
        functionRegistry.register("concat", new StandardSQLFunction("concat", StandardBasicTypes.STRING));
        functionRegistry.register("substr", new StandardSQLFunction("substr", StandardBasicTypes.STRING));
        functionRegistry.register("trim", new StandardSQLFunction("trim", StandardBasicTypes.STRING));
        functionRegistry.register("lower", new StandardSQLFunction("lower", StandardBasicTypes.STRING));
        functionRegistry.register("upper", new StandardSQLFunction("upper", StandardBasicTypes.STRING));
    }

    @Override
    public void contribute(TypeContributions typeContributions, ServiceRegistry serviceRegistry) {
        super.contribute(typeContributions, serviceRegistry);
        typeContributions.contributeJdbcType(VarcharJdbcType.INSTANCE);
    }
}
