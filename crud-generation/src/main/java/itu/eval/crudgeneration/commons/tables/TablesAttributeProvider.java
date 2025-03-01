package itu.eval.crudgeneration.commons.tables;

import itu.eval.crudgeneration.commons.MTable;
import itu.eval.crudgeneration.commons.Variable;
import org.aspectj.weaver.ast.Var;

import java.sql.DatabaseMetaData;
import java.util.List;

public interface TablesAttributeProvider {
    MTable provideAttributes(MTable table, DatabaseMetaData databaseMetaData) throws Exception;
    List<Variable> provideVariables(MTable table, DatabaseMetaData databaseMetaData) throws Exception;
}
