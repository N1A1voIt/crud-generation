package itu.eval.crudgeneration.commons;

import itu.eval.crudgeneration.viewGeneration.ViewCriteria;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MTable {
    String table;
    List<Variable> variables;
    ViewCriteria criteria;

    public Variable getVariable(String name) {
        for (Variable v : variables) {
            if (v.getAttributeName().equals(name)) {
                return v;
            }
        }
        return null;
    }
}
