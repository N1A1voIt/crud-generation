package itu.eval.crudgeneration.commons.fk;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForeignKey {
    private String tableRef;
    private String columnName;
}
