package itu.eval.crudgeneration.commons.tables;

import itu.eval.crudgeneration.commons.MTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

@Service
public class TablesMappingService implements TablesMappingSignature{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public HashMap<String, MTable> provideTablesMetadata() throws SQLException {
        DatabaseMetaData metaData = jdbcTemplate.getDataSource().getConnection().getMetaData();
        ResultSet resultSet = metaData.getTables(null, null, null, new String[]{"TABLE"});
        HashMap<String , MTable> tabMapping = new HashMap<>();
        while (resultSet.next()) {
            String tableName = resultSet.getString("TABLE_NAME");
            MTable tableDescriptor = new MTable();
            tableDescriptor.setTable(tableName);
            ResultSet columnResultSet = metaData.getColumns(null, null, tableName, null);
            tableDescriptor.setVariables(tableElements(columnResultSet,metaData,tableName));
            tabMapping.put(tableName,tableDescriptor);
        }
        return tabMapping;
    }
}
