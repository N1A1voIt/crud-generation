package itu.eval.crudgeneration.springbootgen;

import itu.eval.crudgeneration.commons.MTable;
import itu.eval.crudgeneration.utils.ApiFilePattern;
import itu.eval.crudgeneration.utils.YamlProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class ClassGenerationSignature {
    @Autowired
    private YamlProvider yamlProvider;
    public String getModel(MTable table) throws Exception {
        ApiFilePattern apiFilePattern = yamlProvider.getApiFileYaml();
    }
}
