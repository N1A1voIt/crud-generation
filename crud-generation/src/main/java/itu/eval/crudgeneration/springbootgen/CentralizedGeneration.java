package itu.eval.crudgeneration.springbootgen;

import itu.eval.crudgeneration.ModuleGeneration;
import itu.eval.crudgeneration.commons.MTable;
import itu.eval.crudgeneration.viewGeneration.ViewGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.DatabaseMetaData;

@Service
public class CentralizedGeneration {
    @Autowired
    ModelGenerator modelGenerator;
    @Autowired
    RepositoryGenerator repositoryGenerator;
    @Autowired
    ServiceGenerator serviceGenerator;
    @Autowired
    ControllerGenerator controllerGenerator;
    @Autowired
    ViewGenerationService viewGenerationService;
    @Autowired
    JdbcTemplate jdbcTemplate;
    ClassGenerationSignature classGenerationSignature;

    public String[] generate(ModuleGeneration moduleGeneration) throws Exception {
        DatabaseMetaData d = jdbcTemplate.getDataSource().getConnection().getMetaData();
        MTable mTable = moduleGeneration.getTable();
        MTable view  = moduleGeneration.getView();
        String[] a = new String[4];
        classGenerationSignature = modelGenerator;
        MTable query = viewGenerationService.generateViewClass(mTable, d);
        System.out.println("---------------");
        System.out.println(classGenerationSignature.getClass(view));

//        viewGenerationService.generateQuery(mTable,)
        a[0] = classGenerationSignature.getClass(mTable);
        classGenerationSignature = repositoryGenerator;
        a[1] = classGenerationSignature.getClass(mTable);
        classGenerationSignature = controllerGenerator;
        a[2] = classGenerationSignature.getClass(mTable);
        System.out.println(a[2]);
        classGenerationSignature = serviceGenerator;
        a[3] = classGenerationSignature.getClass(mTable);
//        System.out.println(a[0]);
//        System.out.println(a[1]);
//        System.out.println(a[2]);
//        System.out.println(a[3]);
        return a;
    }
}
