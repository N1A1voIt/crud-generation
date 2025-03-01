package itu.eval.crudgeneration.viewGeneration;

import itu.eval.crudgeneration.commons.MTable;
import itu.eval.crudgeneration.commons.Variable;
import org.springframework.stereotype.Service;

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
