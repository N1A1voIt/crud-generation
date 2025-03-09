package itu.eval.crudgeneration.springbootgen;
import itu.eval.crudgeneration.commons.MTable;
import itu.eval.crudgeneration.utils.ApiFilePattern;
import itu.eval.crudgeneration.utils.YamlProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public abstract class ClassGenerationSignature {
    @Autowired
    YamlProvider yamlProvider;
    public abstract String getClass(MTable table) throws Exception;
    public String generateClassFromTemplate(String templateContent, Map<String, Object> data) throws Exception{
        Configuration cfg = new Configuration(new Version("2.3.30"));
        cfg.setClassForTemplateLoading(this.getClass(), "/templates");
        cfg.setDefaultEncoding("UTF-8");
        StringWriter writer = new StringWriter();
        Template template = new Template("template", new StringReader(templateContent), cfg);
        template.process(data, writer);
        return writer.toString();
    }
    public String toLower(String a){
        a = snakeToPascal(a);
        String b = String.valueOf(a.charAt(0)).toLowerCase();
        return b+a.substring(1);
    }
    public static String snakeToPascal(String snakeCase) {
        if (snakeCase == null || snakeCase.isEmpty()) {
            return snakeCase;
        }

        return Arrays.stream(snakeCase.split("_"))
                .filter(part -> !part.isEmpty())
                .map(part -> Character.toUpperCase(part.charAt(0)) +
                        part.substring(1).toLowerCase())
                .collect(Collectors.joining());
    }
}
