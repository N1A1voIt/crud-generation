package itu.eval.crudgeneration.utils;

import itu.eval.crudgeneration.commons.MTable;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

@Service
public class YamlProvider {
    public ApiFilePattern getApiFileYaml() throws Exception {
        Yaml yaml = new Yaml();
        InputStream inputStream = new FileInputStream("api-file-pattern.yaml");
        Map<String, Object> obj = yaml.load(inputStream);
        Map<String, Object> javaConfig = (Map<String, Object>) obj.get("java");
        ApiFilePattern apiFilePattern = yaml.loadAs(inputStream, ApiFilePattern.class);
        return apiFilePattern;
    }
}
