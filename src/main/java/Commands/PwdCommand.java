package Commands;

public class PwdCommand implements ICommand {
    @Override
    public boolean exec(String[] arguments) {
        System.out.println(CdCommand.getInstance().getCurrentDir());
        return false;
    }
    public static String getName() {
        return "pwd";
    }
}