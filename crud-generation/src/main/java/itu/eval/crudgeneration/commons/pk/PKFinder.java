package itu.eval.crudgeneration.commons.pk;

import org.springframework.stereotype.Service;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

@Service
public class PKFinder {
    public String pkColumnName(DatabaseMetaData metaData, String tableName) throws Exception{
        ResultSet primaryKeyResultSet = metaData.getPrimaryKeys(null, null, tableName);
        String pk = "";
        if (primaryKeyResultSet.next()) {
            pk = primaryKeyResultSet.getString("COLUMN_NAME");
        } else {
            System.out.println("Table " + tableName + " does not have a primary key.");
        }
        return pk;
    }
}
