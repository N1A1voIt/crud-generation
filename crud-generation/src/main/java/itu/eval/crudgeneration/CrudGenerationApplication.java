package itu.eval.crudgeneration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.r2dbc.R2dbcDataAutoConfiguration;

@SpringBootApplication
public class CrudGenerationApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudGenerationApplication.class, args);
    }

}
