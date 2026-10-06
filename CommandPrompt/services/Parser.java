package services;

import entities.Type;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


/**
 * This might need change when using pipes and redirects
**/
@Getter
public class Parser {
    public static Parser instance = new Parser();

    Type getCommandType(String stdin){
        String first = stdin.split(" ")[0];

       try {
           return Type.valueOf(first);
       }catch (Exception e){
           throw new  IllegalArgumentException();
       }
    }

    List<String> getArguments(String stdin){
        List<String> args = new ArrayList<>();

        for(int i = 1; i < stdin.split(" ").length; i++){
            args.add(stdin.split(" ")[i]);
        }
        return args;
    }
}
