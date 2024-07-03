package Commands;

import java.util.Map;

public class TypeCommand implements ICommand {

    Map<String, ICommand> commands;

    public TypeCommand(Map<String, ICommand> commands) {
        this.commands = commands;
    }

    @Override
    public boolean exec(String[] arguments) {
        if (arguments.length > 1) {
            return true;
        }
        if (commands.containsKey(arguments[0].toLowerCase())) {
            System.out.println(arguments[0] + " is a shell builtin");
        } else {
            System.out.println(arguments[0] + ": not found");
        }
        return false;
    }

    public static String getName() {
        return "type";
    }
}
