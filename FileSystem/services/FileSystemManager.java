package services;

import entities.Data;
import entities.FileSystem;
import entities.MetaData;
import entities.Node;

import java.sql.Timestamp;

public class FileSystemManager {

    private final FileSystem fileSystem = FileSystem.getInstance();

    public void create(String path, boolean isFile) {

        String[] parts = path.split("/");
        StringBuilder pathFromRoot = new StringBuilder();
        Node parent = null;

        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            if (part.isEmpty()) {
                continue;
            }

            pathFromRoot.append("/").append(part);
            boolean isLast = i == parts.length - 1;

            Node node = fileSystem.findNodeByPath(pathFromRoot.toString());

            if (node == null) {
                node = Node.create(
                        pathFromRoot.toString(),
                        part,
                        parent,
                        isFile && isLast
                );
            }
            node.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            fileSystem.save(node);
            parent = node;
        }
    }

    // either the metadata has been changed or the data itself has changed
    public void update(String path, MetaData metaData, Data data, boolean isFile) {
        Node node = fileSystem.findNodeByPath(path);

        if (!isFile && metaData != null) {
            node.setMetaData(metaData);
            node.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            fileSystem.save(node);
        }
        if (metaData != null) {
            node.setMetaData(metaData);
        } else {
            node.setData(data);
        }
        node.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        fileSystem.save(node);
    }

    public void delete(Node node) {
        if (node == null) return;

        if (node.getIsFile()) {
            fileSystem.delete(node);
            return;
        }

        for (Node child : node.getChildren()) {
            delete(child);
        }
    }

    public void display(Node node){
        if(node == null) return;


        System.out.println("Node: " + node.getPathFromRoot() + ", isFile: " + node.getIsFile());
        for (Node child : node.getChildren()) {
            display(child);
        }
    }

    public void displayFileSystem(){
        var roots = fileSystem.getNodesByPath();
        for (var root : roots.entrySet()) {
            display(root.getValue());
        }
    }
}
