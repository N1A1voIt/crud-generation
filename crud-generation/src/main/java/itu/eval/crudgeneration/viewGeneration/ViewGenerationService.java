package itu.eval.crudgeneration.viewGeneration;

import itu.eval.crudgeneration.commons.MTable;
import itu.eval.crudgeneration.commons.Variable;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

@Service
public class ViewGenerationService implements ViewGenerationSignatures{
    @Override
    public String  generateQuery(MTable mTable, ViewCriteria criteria) {
        String initTab = mTable.getTable();
        String parameters = getAttribute(mTable, criteria);
        String query = "SELECT "+parameters+" FROM "+initTab+"";
        for (int i = 0; i < mTable.getVariables().size(); i++) {
            if (mTable.getVariables().get(i).getKeyType().equals("FK")){
                query += " JOIN "+mTable.getVariables().get(i).getRefTable()
                        + " ON "+mTable.getTable() + "." + mTable.getVariables().get(i).getAttributeName()+" = "
                        + mTable.getVariables().get(i).getRefTable()+"."+mTable.getVariables().get(i).getRefColumn();
            }
        }
        String view = "CREATE VIEW v_"+mTable.getTable()+" AS "+query;
        return view;
    }

    @Override
    public MTable generateViewClass(MTable mTable,DatabaseMetaData databaseMetaData) throws Exception {
        String query = generateQuery(mTable,mTable.getCriteria());
        persistView(databaseMetaData,query);

        return null;
    }

    @Override
    public void persistView(DatabaseMetaData databaseMetaData,String query) throws Exception {
        if (query == null || query.isEmpty()) {
            throw new IllegalArgumentException("No query generated to persist.");
        }
        Connection connection = databaseMetaData.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        System.out.println("View created successfully.");
    }

    private String getAttribute(MTable mTable,ViewCriteria viewCriteria) {
        String s = "";
        String tab = mTable.getTable();
        String column = "";
        for (int i = 0; i < mTable.getVariables().size(); i++) {
            boolean isFk = false;
            if (mTable.getVariables().get(i).getKeyType().equals("FK")) {
                isFk = true;
                tab = mTable.getVariables().get(i).getRefTable();
            }
            else tab = mTable.getTable();
            column = mTable.getVariables().get(i).getAttributeName();
            if (isFk) {
                column = viewCriteria.getCriteria().get(mTable.getVariables().get(i).getAttributeName());
            }
            s += tab + "." + column;
            if (i!= mTable.getVariables().size()-1){
                s += ",";
            }
            tab = mTable.getTable();
        }
        return s;
    }
}
