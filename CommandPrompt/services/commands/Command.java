package services.commands;

import entities.CommandContext;

import java.io.IOException;

public interface Command {
    boolean shouldRun(CommandContext context);
    boolean validate(CommandContext context);
    void execute(CommandContext context);
}
