// Uncomment this block to pass the first stage
// import java.util.Scanner;

import Commands.ICommand;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        // Initialize implemented commands
        Map<String, ICommand> commands = new HashMap<>();
        // Uncomment this block to pass the first stage
        while (true) {
            System.out.print("$ ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if (commands.containsKey(input.toLowerCase())) {
                commands.get(input.toLowerCase()).exec(null);
            } else {
                System.out.println(input + ": command not found");
            }
        }
    }
}
