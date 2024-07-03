package Commands;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TypeCommand implements ICommand {

    Map<String, ICommand> commands;
    Map<String, Set<String>> executables;


    public TypeCommand(String path, Map<String, ICommand> commands) {
        this.commands = commands;
        executables = new HashMap<>();
        for (String dir : path.split(":")) {
            File[] files = new File(dir).listFiles();
            if (files != null) {
                Set<String> execs = Stream.of(files)
                        .filter(file -> !file.isDirectory())
                        .map(File::getName)
                        .collect(Collectors.toSet());
                executables.put(dir, execs);
            }
        }
    }

    @Override
    public boolean exec(String[] arguments) {
        if (arguments.length > 1) {
            return true;
        }
        if (commands.containsKey(arguments[0].toLowerCase())) {
            System.out.println(arguments[0]+" is a shell builtin");
            return false;
        }
        for(Map.Entry<String, Set<String>> entry : executables.entrySet()) {
            String dir = entry.getKey();
            Set<String> execsInDir = entry.getValue();

            if (execsInDir.contains(arguments[0])) {
                System.out.println(arguments[0]+" is "+dir+"/"+arguments[0]);
                return false;
            }

        }
        System.out.println(arguments[0]+": not found");
        return false;
    }

    public static String getName() {
        return "type";
    }
}
