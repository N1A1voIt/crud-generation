package itu.eval.crudgeneration.commons.tables;

import itu.eval.crudgeneration.commons.MTable;

import java.sql.SQLException;
import java.util.HashMap;

public interface TablesMappingSignature {
    HashMap<String , MTable> provideTablesMetadata() throws SQLException;
}
