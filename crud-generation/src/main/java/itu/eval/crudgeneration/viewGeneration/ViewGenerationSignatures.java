package itu.eval.crudgeneration.viewGeneration;

import itu.eval.crudgeneration.commons.MTable;

import javax.xml.crypto.Data;
import java.sql.DatabaseMetaData;
import java.util.HashMap;

public interface ViewGenerationSignatures {
    String generateQuery(MTable mTable, ViewCriteria viewCriteria);
    MTable generateViewClass(MTable mTable, DatabaseMetaData databaseMetaData) throws Exception;
    void persistView(DatabaseMetaData databaseMetaData,String query) throws Exception;
}
