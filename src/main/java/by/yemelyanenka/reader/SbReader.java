package by.yemelyanenka.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class SbReader {

    public static  void readAllSbFiles(Path directory) throws IOException {
        List<Path> sbFiles = findSbFiles(directory);

        for(Path file : sbFiles){
            List<String> lines = Files.readAllLines(file);
            for(String line : lines){
                System.out.println(line);
            }
        }
    }
    public static List<Path> findSbFiles(Path directory) throws IOException {

        return Files.walk(directory)
                    .filter(p -> p.toFile().isFile() && p.toString().endsWith(".sb")).collect(Collectors.toList());
    }
}
