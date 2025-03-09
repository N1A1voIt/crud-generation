package itu.eval.crudgeneration.springbootgen;

import itu.eval.crudgeneration.commons.MTable;
import itu.eval.crudgeneration.utils.ApiFilePattern;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
@Primary
public class ServiceGenerator  extends ClassGenerationSignature{
    @Value("${group}")
    String group;
    @Override
    public String getClass(MTable table) throws Exception {
        ApiFilePattern apiFilePattern = super.yamlProvider.getApiFileYaml();
        Map<String, Object> data = new HashMap<>();
        data.put("modelName",  snakeToPascal(table.getTable()));
        data.put("modelNameLower", toLower(table.getTable()));
        data.put("group", group);
        data.put("tableName", table.getTable());
        data.put("fields", table.getVariables());
        return generateClassFromTemplate(apiFilePattern.getService().getContent(), data);
    }

}
