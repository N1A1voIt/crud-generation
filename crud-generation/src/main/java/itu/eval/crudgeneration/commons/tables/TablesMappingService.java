package itu.eval.crudgeneration.commons.tables;

import itu.eval.crudgeneration.commons.MTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class TablesMappingService implements TablesMappingSignature{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private TablesAttProvidersService tableElementsProvider;

    @Override
    public HashMap<String, MTable> provideTablesMetadata() throws Exception {
        TablesAttributeProvider tablesAttributeProvider = tableElementsProvider;
        DatabaseMetaData metaData = jdbcTemplate.getDataSource().getConnection().getMetaData();
        try (ResultSet resultSet = metaData.getTables(null, null, null, new String[]{"TABLE"})) {
            HashMap<String, MTable> tabMapping = new HashMap<>();  // Preserve insertion order

            while (resultSet.next()) {
                String tableName = resultSet.getString("TABLE_NAME");

                MTable tableDescriptor = new MTable();
                tableDescriptor.setTable(tableName);

                // Consider limiting metadata calls for performance
                tableDescriptor.setVariables(tablesAttributeProvider.provideVariables(
                        tableDescriptor,
                        metaData
                ));

                tabMapping.put(tableName.toLowerCase(), tableDescriptor);  // Case-insensitive key
            }
            System.out.println("Hello!!!");

            return tabMapping;
        } catch (SQLException e) {
            throw new RuntimeException("Error processing database metadata", e);
        }
//        return tabMapping;
    }
}
