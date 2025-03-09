package itu.eval.crudgeneration;

import itu.eval.crudgeneration.cli.ConcreteGenerationProcess;
import itu.eval.crudgeneration.cli.GenerationProcessSignatures;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerTest {
    @Autowired
    ConcreteGenerationProcess genSig;
    @GetMapping("/generate")
    public String generate() throws Exception {
        genSig.generate();
        return "a";
    }
}
