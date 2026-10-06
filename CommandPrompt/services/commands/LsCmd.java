package services.commands;

import entities.CommandContext;
import entities.FileSystem;
import entities.Node;
import entities.Type;

import java.util.List;

public class LsCmd implements Command {
    FileSystem fileSystem = FileSystem.getInstance();

    @Override
    public boolean shouldRun(CommandContext context)  {
        return Type.ls.equals(context.getType());
    }

    @Override
    public boolean validate(CommandContext context)  {
        if(context.getArgs().size() > 1) {
            context.setStderr(context.getStderr() + context.getArgs().get(1));
            return false;
        }

        var pwd = context.getArgs().size() == 1 ? context.getArgs().get(0) : context.getCurrentWorkingDirectory();

        Node node  = fileSystem.findNodeByPath(pwd);

        if(node == null) {
            context.setStderr("No such directory: " + pwd);
            return false;
        }
        return true;
    }

    @Override
    public void execute(CommandContext context) {
        var pwd = context.getArgs().size() == 1 ? context.getArgs().get(0) : context.getCurrentWorkingDirectory();

        Node node = fileSystem.findNodeByPath(pwd);
        List<Node> children = node.getChildren();

        StringBuilder sout = new StringBuilder();

        for(int i = 0; i < children.size(); i++) {
            var child = children.get(i);
            if(i != 0) {
                sout.append(" ");
            }
            sout.append(child.getName());
        }

        System.out.println(sout.toString());
    }
}
