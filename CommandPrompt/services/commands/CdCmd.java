package services.commands;

import entities.CommandContext;
import entities.FileSystem;
import entities.Node;
import entities.Type;

import java.util.List;
import java.util.Objects;

public class CdCmd implements Command {
    private static final FileSystem fs = FileSystem.getInstance();

    @Override
    public boolean shouldRun(CommandContext context) {
        return Type.cd.equals(context.getType());
    }

    @Override
    public boolean validate(CommandContext context) {
        var args = context.getArgs();

        if(args.size() > 1) {
            context.setStderr("Too many arguments");
            return false;
        }
        return true;
    }

    @Override
    public void execute(CommandContext context)  {
        String pwd = context.getCurrentWorkingDirectory();
        var args = context.getArgs();
        if(args.isEmpty()) {
            return;
        }

        String arg = args.get(0);

        if(Objects.equals(arg, ".")){
            return;
        }
        if(Objects.equals(arg, "..")){
            if(pwd.isEmpty()){
                return;
            } else {
                context.setCurrentWorkingDirectory(pwd.substring(0, pwd.length() - 1));
                return;
            }
        }

        if(arg.charAt(0) == '~'){
            context.setCurrentWorkingDirectory(arg.substring(1));
            return;
        }

        Node node = fs.findNodeByPath(pwd);
        if(node == null){
            context.setStderr("Node not found");
            return;
        }

        List<Node> children = node.getChildren();
        boolean found = false;

        for(Node child : children){
            if(child.getName().equals(arg)){
                found = true;
                break;
            }
        }

        if(!found){
            context.setStderr("Node not found");
            return;
        }
        context.setCurrentWorkingDirectory(context.getCurrentWorkingDirectory() + arg);
    }
}
