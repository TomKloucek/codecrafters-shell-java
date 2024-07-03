package Commands;

public class ExitCommand implements ICommand{
    @Override
    public boolean exec(String[] arguments) {
        return true;
    }
    public static String getName() {
        return "exit";
    }
}
