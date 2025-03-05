package itu.eval.crudgeneration.commons.tables;

import itu.eval.crudgeneration.commons.MTable;
import itu.eval.crudgeneration.commons.Variable;
import itu.eval.crudgeneration.commons.fk.ForeignKey;
import itu.eval.crudgeneration.commons.fk.ForeignKeyProvider;
import itu.eval.crudgeneration.commons.pk.PKFinder;
import itu.eval.crudgeneration.types.JsonTypeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class TablesAttProvidersService implements TablesAttributeProvider{
    @Autowired
    ForeignKeyProvider foreignKeyProvider;
    @Autowired
    PKFinder pkFinder;

    @Override
    public MTable provideAttributes(MTable table, DatabaseMetaData databaseMetaData) throws Exception{
        table.setVariables(provideVariables(table,databaseMetaData));
        return table;
    }

    @Override
    public List<Variable> provideVariables(MTable table, DatabaseMetaData databaseMetaData) throws Exception{
        List<Variable> variables = new ArrayList<>();
        HashMap<String,ForeignKey> foreignKeys = foreignKeyProvider.getForeignKeys(table,databaseMetaData);
        ResultSet columnResultSet = databaseMetaData.getColumns(null, null, table.getTable(), null);
        String pk = pkFinder.pkColumnName(databaseMetaData, table.getTable());
        while (columnResultSet.next()) {
            String columnName = columnResultSet.getString("COLUMN_NAME");
            String typeName = columnResultSet.getString("TYPE_NAME");
            Variable variable = new Variable();
            variable.setAttributeName(columnName);
            variable.setVariableName(attributeNameToVariablename(columnName));
            variable.setDatabaseType(typeName);
            variable.setVariableType(JsonTypeProvider.getTypeMap("java").get(typeName));
            if (foreignKeys.containsKey(columnName)) {
                variable.setKeyType("FK");
                variable.setRefTable(foreignKeys.get(columnName).getTableRef());
                variable.setRefColumn(pkFinder.pkColumnName(databaseMetaData,foreignKeys.get(columnName).getTableRef()));
            }
            if (pk.equals(columnName)) variable.setKeyType("PK");
            else variable.setKeyType("Normal");
            variables.add(variable);
        }
        return variables;
    }
    private String attributeNameToVariablename(String attributeName) {
        StringBuilder variableName = new StringBuilder();
        String[] words = attributeName.split("_");
        variableName.append(words[0].toLowerCase());
        for (int i = 1; i < words.length; i++) {
            variableName.append(words[i].substring(0, 1).toUpperCase());
            variableName.append(words[i].substring(1).toLowerCase());
        }
        return variableName.toString();
    }

}
