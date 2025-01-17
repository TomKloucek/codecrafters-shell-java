// Uncomment this block to pass the first stage
// import java.util.Scanner;

import Commands.*;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        // Initialize implemented commands
        Map<String, ICommand> commands = new HashMap<>();
        commands.put(ExitCommand.getName(), new ExitCommand());
        commands.put(EchoCommand.getName(), new EchoCommand());
        commands.put(TypeCommand.getName(), new TypeCommand(System.getenv("PATH"), commands));
        commands.put(PwdCommand.getName(), new PwdCommand());
        commands.put(CdCommand.getName(), CdCommand.getInstance());

        while (true) {
            System.out.print("$ ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            String[] parts = input.split(" ");
            String command = parts[0];
            String[] arguments = new String[parts.length - 1];
            System.arraycopy(parts, 1, arguments, 0, parts.length - 1);

            if (commands.containsKey(command.toLowerCase())) {
                if (commands.get(command.toLowerCase()).exec(arguments)) {
                    break;
                }
            } else {
                if (ExternalProgram.programExists(parts)) {
                    ExternalProgram.executeProgram(input);
                } else {
                    System.out.println(input + ": command not found");
                }
            }
        }
    }
}
