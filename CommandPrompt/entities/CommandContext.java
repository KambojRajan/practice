package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CommandContext {
    Type type;
    List<String> args;
    String currentWorkingDirectory;
    String stdin;
    String stdout;
    String stderr;
}
