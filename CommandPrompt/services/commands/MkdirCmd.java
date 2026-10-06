package services.commands;

import entities.CommandContext;
import entities.FileSystem;
import entities.Node;
import entities.Type;
import services.FileSystemManager;

public class MkdirCmd implements Command{
    FileSystem fileSystem = FileSystem.getInstance();
    FileSystemManager fileSystemManager = new FileSystemManager();


    @Override
    public boolean shouldRun(CommandContext context) {
        return Type.mkdir.equals(context.getType());
    }

    @Override
    public boolean validate(CommandContext context) {
        if(context.getArgs().size() != 1) {
            context.setStderr(context.getStderr() + context.getArgs().get(1));
            return false;
        }

        var pwd = context.getArgs().get(0);

        Node node  = fileSystem.findNodeByPath(pwd);

        if(node != null) {
            context.setStderr("Node already exists!");
            return false;
        }
        return true;
    }

    @Override
    public void execute(CommandContext context) {
        String pwd = context.getCurrentWorkingDirectory() + context.getArgs().get(0);
        fileSystemManager.create(pwd, false);
    }
}
