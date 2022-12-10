package compilador.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import compilador.lexic.Scanner;
import compilador.sintactic.Parser;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.SymbolFactory;
//import compilador.sintactic.semantic.SemanticAnalysis;

public class Main2021 {

    static final String PROMPT = "Specify the name of the directory of tests you want to compile (type 'exit' to exit): ";

    public static void main(String[] args) throws Exception, IOException {
        String dirName;
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.print(PROMPT);
        dirName = reader.readLine();
        while (!"exit".equals(dirName)) {
            
            Set<String> files = listFiles(dirName);

            for (String fileName : files) {
                System.out.println("======= Running " + fileName + " =======");
                Thread.sleep(1000);
                Scanner scanner = new Scanner(new FileReader(dirName + "/" + fileName));

                SymbolFactory sf = new ComplexSymbolFactory();
                Parser parser = new Parser(scanner, sf);

                parser.parse();
                
                Thread.sleep(1000);
                System.out.println("======= Finished " + fileName + " =======");
            }
            

            System.out.print(PROMPT);
            dirName = reader.readLine();
        }
        

    }

    public static Set<String> listFiles(String dir) throws IOException {
        Set<String> fileList = new HashSet<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(dir))) {
            for (Path path : stream) {
                if (!Files.isDirectory(path)) {
                    fileList.add(path.getFileName()
                            .toString());
                }
            }
        }
        return fileList;
    }
}
