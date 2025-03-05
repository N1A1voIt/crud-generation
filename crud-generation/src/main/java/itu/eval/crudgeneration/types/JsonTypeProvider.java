package itu.eval.crudgeneration.types;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class JsonTypeProvider {
    public static Map<String,String> getTypeMap(String language) throws Exception {

        InputStream inputStream = new ClassPathResource("type-mapping.json").getInputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Map<String, String>> jsonMap = objectMapper.readValue(inputStream, Map.class);
        Map<String, String> javaTypes = jsonMap.get(language);
        return javaTypes;
    }
}
