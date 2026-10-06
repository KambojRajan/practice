package entities;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Getter
public class FileSystem {

    private static final FileSystem INSTANCE = new FileSystem();

    private final Map<String, Node> nodesByPath = new HashMap<>();
    private long nextId = 1;

    private FileSystem() {
    }

    public static FileSystem getInstance() {
        return INSTANCE;
    }

    public Node findNodeByPath(String pathFromRoot) {
        return nodesByPath.get(pathFromRoot);
    }

    public void save(Node node) {
        if (node.getId() == null) {
            node.setId(nextId++);
        }
        nodesByPath.put(node.getPathFromRoot(), node);

        Node parent = node.getParent();
        if (parent != null) {
            if (parent.getChildren() == null) {
                parent.setChildren(new ArrayList<>());
            }
            parent.getChildren().add(node);
        }
    }

    public void delete(Node node){
        if(node == null) return;
        nodesByPath.remove(node.getPathFromRoot());
        Node parent = node.getParent();
        if (parent != null) {
            parent.getChildren().remove(node);
        }
    }

    public List<Node> getByParent(Node node){
        return node.getChildren();
    }
}
