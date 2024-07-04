package Commands;

import java.io.File;

public class CdCommand implements ICommand {
    private static CdCommand instance;

    private String currentDir;

    private CdCommand() {
        this.currentDir = System.getProperty("user.dir");
    }

    @Override
    public boolean exec(String[] arguments) {
        if (arguments.length > 1) {
            return true;
        }
        if (arguments[0].startsWith(".")) {
            if (arguments[0].startsWith("..")) {
                File dir = new File(currentDir);
                for (int i = 0; i < arguments[0].split("../", -1).length-1; i++) {
                    dir = new File(dir.getParent());
                }
                currentDir = dir.getAbsolutePath();
            } else {
                if (checkDirExists(currentDir+arguments[0].split("\\.")[1])) {
                    currentDir = currentDir+arguments[0].split("\\.")[1];
                } else {
                    System.out.println("cd: "+arguments[0]+": No such file or directory");
                }
            }
        } else if (arguments[0].startsWith("~")) {

        } else {
            if (checkDirExists(arguments[0])) {
                currentDir = arguments[0];
            } else {
                System.out.println("cd: "+arguments[0]+": No such file or directory");
            }
        }
        return false;
    }

    private boolean checkDirExists(String dirPath) {
        File directory = new File(dirPath);

        return directory.exists() && directory.isDirectory();
    }

    public String getCurrentDir() {
        return currentDir;
    }

    public static CdCommand getInstance() {
        if (instance == null) {
            instance = new CdCommand();
        }
        return instance;
    }
    public static String getName() {
        return "cd";
    }
}
