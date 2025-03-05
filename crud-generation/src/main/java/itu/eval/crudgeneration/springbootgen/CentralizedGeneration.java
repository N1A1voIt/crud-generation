package itu.eval.crudgeneration.springbootgen;

import itu.eval.crudgeneration.commons.MTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    ClassGenerationSignature classGenerationSignature;

    public String[] generate(MTable mTable) throws Exception {
        String[] a = new String[4];
        classGenerationSignature = modelGenerator;
        a[0] = classGenerationSignature.getClass(mTable);
        classGenerationSignature = repositoryGenerator;
        a[1] = classGenerationSignature.getClass(mTable);
        classGenerationSignature = controllerGenerator;
        a[2] = classGenerationSignature.getClass(mTable);
        classGenerationSignature = serviceGenerator;
        a[3] = classGenerationSignature.getClass(mTable);
        System.out.println(a[0]);
        System.out.println(a[1]);
        System.out.println(a[2]);
        System.out.println(a[3]);
        return a;
    }
}
