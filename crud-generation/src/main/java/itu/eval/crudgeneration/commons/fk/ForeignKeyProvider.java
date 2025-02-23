package itu.eval.crudgeneration.commons.fk;

import itu.eval.crudgeneration.commons.MTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ForeignKeyProvider {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ForeignKey> getForeignKeys(MTable table) throws Exception {
            DatabaseMetaData metaData = jdbcTemplate.getDataSource().getConnection().getMetaData();
            return presentsForeignKeys(metaData,table.getTable());
    }

    private List<ForeignKey> presentsForeignKeys(DatabaseMetaData metaData,String tabName) throws SQLException {
        List<ForeignKey> fks = new ArrayList<>();
        ResultSet foreignKeys = metaData.getImportedKeys(null,null,tabName);
        while (foreignKeys.next()){
            String colName = foreignKeys.getString("FKCOLUMN_NAME");
            String referencedTableName = foreignKeys.getString("PKTABLE_NAME");
            ForeignKey fkTableRef = new ForeignKey();
            fkTableRef.setColumnName(colName);
            fkTableRef.setTableRef(referencedTableName);
            fks.add(fkTableRef);
        }
        return fks;
    }

}
