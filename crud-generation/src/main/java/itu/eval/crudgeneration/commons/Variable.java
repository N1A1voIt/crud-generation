package itu.eval.crudgeneration.commons;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Variable {
    private String databaseType;
    private String keyType;
    private String attributeName;
    private String refTable;
    private String refColumn;
}
