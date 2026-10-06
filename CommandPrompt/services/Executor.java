package services;

import entities.CommandContext;
import entities.Type;
import services.commands.CdCmd;
import services.commands.Command;
import services.commands.LsCmd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Executor {
    List<Command> commands = new ArrayList<>();
    String stdin, stdout, stderr;
    private static final Parser parser = Parser.instance;


    public Executor(){
        commands.add(new LsCmd());
        commands.add(new CdCmd());
        // etc
        stdin = stdout = stderr = "";
    }

    public void execute(String stdin, String currentDir) throws IOException {

        Type type = parser.getCommandType(stdin);
        List<String> args = parser.getArguments(stdin);;
        CommandContext context = new CommandContext(
                type,
                args,
                currentDir,
                stdin,
                stdout,
                stderr
        );
        for(var command : commands){
           if(command.shouldRun(context) && command.validate(context)) {
               command.execute(context);
               if(context.getStderr() != null){
                   throw new IOException(context.getStderr());
               }
           }
        }
    }
}
