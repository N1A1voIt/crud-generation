package itu.eval.crudgeneration.springbootgen;

import itu.eval.crudgeneration.commons.MTable;
import itu.eval.crudgeneration.utils.ApiFilePattern;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ModelGenerator extends ClassGenerationSignature{
    @Override
    public String getClass(MTable table) throws Exception {
        ApiFilePattern apiFilePattern = super.yamlProvider.getApiFileYaml();
        Map<String, Object> data = new HashMap<>();
        data.put("modelName", table.getTable());
        data.put("modelNameLower", toLower(table.getTable()));
        data.put("group", table.getTable());
        data.put("tableName", table.getTable());
        data.put("fields", table.getVariables());
        return generateClassFromTemplate(apiFilePattern.getModel().getContent(), data);
    }

}
