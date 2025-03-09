package itu.eval.crudgeneration.cli;

import itu.eval.crudgeneration.ModuleGeneration;
import itu.eval.crudgeneration.commons.MTable;
import itu.eval.crudgeneration.commons.Variable;
import itu.eval.crudgeneration.commons.tables.TablesMappingService;
import itu.eval.crudgeneration.commons.tables.TablesMappingSignature;
import itu.eval.crudgeneration.springbootgen.CentralizedGeneration;
import itu.eval.crudgeneration.viewGeneration.ViewCriteria;
import itu.eval.crudgeneration.viewGeneration.ViewGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

@Service
public abstract class GenerationProcessSignatures {
    @Autowired
    TablesMappingService tablesMappingService;
    @Autowired
    ViewGenerationService viewGenerationService;
    @Autowired
    CentralizedGeneration centralizedGeneration;
    ModuleGeneration getTables() throws Exception {
        TablesMappingSignature tablesMappingSignature = tablesMappingService;
        ViewCriteria viewCriteria = new ViewCriteria();

        HashMap<String,MTable> tables = tablesMappingSignature.provideTablesMetadata();
        System.out.println("Available tables:");
        int i = 1;
        for (String key : tables.keySet()) {
            System.out.println(i + ". " + key);
            i++;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please choose a table (enter the number): ");
        int choice = scanner.nextInt();

        String selectedKey = null;
        if (choice >= 1 && choice <= tables.size()) {
            selectedKey = (String) tables.keySet().toArray()[choice - 1];
            System.out.println("You selected table: " + selectedKey);

        } else {
            System.out.println("Invalid choice. Please select a number from the list.");
            return null;
        }
        MTable selectedTable = tables.get(selectedKey);
        List<Variable> variables = new ArrayList<>();
        ModuleGeneration moduleGeneration = new ModuleGeneration();
        for (int j = 0; j < selectedTable.getVariables().size(); j++) {
            System.out.println(selectedTable.getVariables().get(j).getKeyType());
            if (selectedTable.getVariables().get(j).getKeyType().equals("FK")) {
                System.out.print("Please enter the referenced key name for foreign key " +
                        selectedTable.getVariables().get(j).getAttributeName() + ": ");
                MTable temp = tables.get(selectedTable.getVariables().get(j).getRefTable());
                String nameRef = scanner.next();
                variables.add(temp.getVariable(nameRef));
                viewCriteria.getCriteria().put(selectedTable.getVariables().get(j).getAttributeName(), nameRef);
                continue;
            }
            variables.add(selectedTable.getVariables().get(j));
        }
        MTable view = new MTable();
        view.setTable("v_"+selectedTable.getTable());
        view.setVariables(variables);
        selectedTable.setCriteria(viewCriteria);
        moduleGeneration.setTable(selectedTable);
        moduleGeneration.setView(view);
        return moduleGeneration;
    }
    public String[] generate() throws Exception {
        ModuleGeneration table = getTables();
        String[] list = centralizedGeneration.generate(table);
        System.out.println(list[0]);
        return list;
//        return null;
    }

}
