package itu.eval.crudgeneration.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import itu.eval.crudgeneration.commons.MTable;
import org.springframework.core.io.ClassPathResource;
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
        InputStream inputStream = new ClassPathResource("api-file-pattern.yaml").getInputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> obj = yaml.load(inputStream);
        Map<String, Object> javaConfig = (Map<String, Object>) obj.get("nestjs");
        return objectMapper.convertValue(javaConfig, ApiFilePattern.class);
    }
}
