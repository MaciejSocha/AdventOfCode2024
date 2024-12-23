package utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReader {
    public static String read(String filePath) {
        StringBuilder builder = new StringBuilder();
        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach(s -> builder.append(s).append("\n"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return  builder.toString();
    }
}
