package services.commands;

import entities.CommandContext;
import entities.Type;

public class PwdCmd implements Command{
    @Override
    public boolean shouldRun(CommandContext context) {
        return Type.mkdir.equals(context.getType());
    }

    @Override
    public boolean validate(CommandContext context) {
        if (!context.getArgs().isEmpty()) {
            context.setStderr(context.getStderr() + context.getArgs().get(1));
            return false;
        }
        return true;
    }

    @Override
    public void execute(CommandContext context) {
        System.out.println(context.getCurrentWorkingDirectory());
    }
}
