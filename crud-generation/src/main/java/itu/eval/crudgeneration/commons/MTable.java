package itu.eval.crudgeneration.commons;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MTable {
    String table;
    List<Variable> variables;
}
