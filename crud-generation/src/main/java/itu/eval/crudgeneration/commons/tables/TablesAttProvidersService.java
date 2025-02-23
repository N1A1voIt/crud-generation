package itu.eval.crudgeneration.commons.tables;

import itu.eval.crudgeneration.commons.MTable;
import itu.eval.crudgeneration.commons.Variable;

import java.sql.DatabaseMetaData;
import java.util.List;

public class TablesAttProvidersService implements TablesAttributeProvider{
    @Override
    public MTable provideAttributes(MTable table, DatabaseMetaData databaseMetaData) {
        return null;
    }

    @Override
    public List<Variable> provideVariables(MTable table, DatabaseMetaData databaseMetaData) {
        return List.of();
    }
}
