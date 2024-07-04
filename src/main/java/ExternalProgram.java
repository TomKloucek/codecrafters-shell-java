import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExternalProgram {

    public static boolean programExists(String[] arguments) {
        Map<String, Set<String>> executables;
        String path = System.getenv("PATH");
        executables = new HashMap<>();
        for (String dir : path.split(File.pathSeparator)) {
            File[] files = new File(dir).listFiles();
            if (files != null) {
                Set<String> execs = Stream.of(files)
                        .filter(file -> !file.isDirectory())
                        .map(File::getName)
                        .collect(Collectors.toSet());
                executables.put(dir, execs);
            }
        }

        for(Map.Entry<String, Set<String>> entry : executables.entrySet()) {
            Set<String> execsInDir = entry.getValue();
            if (execsInDir.contains(arguments[0])) {
                return true;
            }
        }
        return false;
    }

    public static boolean executeProgram(String command) {
        try {
            // Start the process
            Process process = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", command});

            // Capture the output of the command
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
