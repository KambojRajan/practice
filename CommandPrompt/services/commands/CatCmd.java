package services.commands;

import entities.CommandContext;

public class CatCmd implements Command{
    @Override
    public boolean shouldRun(CommandContext context) {
        return false;
    }

    @Override
    public boolean validate(CommandContext context) {
        return false;
    }

    @Override
    public void execute(CommandContext context) {

    }
}
