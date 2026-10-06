package entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@Builder
public class Node {
    Long id;
    String name;
    String pathFromRoot;
    Boolean isFile;
    Timestamp createdAt;
    Timestamp updatedAt;
    Permissions permissions;
    Data data;
    MetaData metaData;


    Node parent;
    List<Node> children;


    public static Node create(String pathFromRoot, String name, Node parent, boolean isFile){
        return Node.builder()
                .name(name)
                .pathFromRoot(pathFromRoot)
                .isFile(isFile)
                .parent(parent)
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .updatedAt(new Timestamp(System.currentTimeMillis()))
                .permissions(new Permissions())
                .build();
    }
}
