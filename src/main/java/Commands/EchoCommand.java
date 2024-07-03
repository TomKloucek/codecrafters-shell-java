package Commands;

public class EchoCommand implements ICommand {
    @Override
    public boolean exec(String[] arguments) {
        System.out.println(String.join(" ", arguments));
        return false;
    }

    public static String getName() {
        return "echo";
    }
}
