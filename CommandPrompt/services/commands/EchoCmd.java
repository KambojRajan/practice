package services.commands;

import entities.CommandContext;
import entities.Type;

public class EchoCmd implements Command{

    @Override
    public boolean shouldRun(CommandContext context) {
        return Type.echo.equals(context.getType());
    }

    @Override
    public boolean validate(CommandContext context) {
        return true;
    }

    @Override
    public void execute(CommandContext context) {
        StringBuilder sb = new StringBuilder();
        for (var arg : context.getArgs()){
            sb.append(arg).append(" ");
        }

        System.out.println(sb.toString());
    }
}
