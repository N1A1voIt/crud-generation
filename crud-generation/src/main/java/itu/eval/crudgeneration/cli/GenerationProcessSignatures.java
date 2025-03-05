package itu.eval.crudgeneration.cli;

import itu.eval.crudgeneration.commons.MTable;
import itu.eval.crudgeneration.commons.tables.TablesMappingService;
import itu.eval.crudgeneration.commons.tables.TablesMappingSignature;
import itu.eval.crudgeneration.springbootgen.CentralizedGeneration;
import itu.eval.crudgeneration.viewGeneration.ViewGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
    MTable getTables() throws Exception {
        TablesMappingSignature tablesMappingSignature = tablesMappingService;
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
        return selectedTable;
    }
    public String[] generate() throws Exception {
        MTable table = getTables();
        String[] list = centralizedGeneration.generate(table);
        System.out.println(list[0]);
        return list;
//        return null;
    }

}
