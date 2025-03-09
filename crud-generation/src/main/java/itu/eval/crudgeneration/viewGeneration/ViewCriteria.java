package itu.eval.crudgeneration.viewGeneration;

import java.util.HashMap;

public class ViewCriteria {
    private HashMap<String, String> criteria;

    public HashMap<String,String> getCriteria() {
        if (criteria == null) {criteria = new HashMap<>();}
        return criteria;
    }
}
