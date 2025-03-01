package itu.eval.crudgeneration.viewGeneration;

import itu.eval.crudgeneration.commons.MTable;

import java.util.HashMap;

public interface ViewGenerationSignatures {
    String generateQuery(MTable mTable, ViewCriteria viewCriteria);
}
