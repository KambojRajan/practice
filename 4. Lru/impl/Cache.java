package impl;

import lombok.AllArgsConstructor;

import java.util.*;

@AllArgsConstructor
public class Cache {
    Map<Integer, Node> keys;
    SingularLinkedList nodes;
    int capacity;


    public Cache(int capacity) {
        keys = new HashMap<>();
        nodes = new SingularLinkedList();
        this.capacity = capacity;
    }


    public void add(Pair pair) {
        /**
         * check -> the cache is not full
         * check -> This key must not already exist
         * do -> add the new pair to the cache and make it most recently used
         * update both the nodes and keys
         **/

        if (keys.containsKey(pair.key)) { // use update if this is needed
            return;
        }

        if (capacity == keys.size()) {
            evict();
        }

        Node node = new Node(pair.key, pair.value);
        keys.put(pair.key, node);
        nodes.add(node);
    }

    public void update(Pair pair) {
        if (!keys.containsKey(pair.key)) {
            return;
        }

        Node node = keys.get(pair.key);
        node.value = pair.value;
        keys.put(pair.key, node);

        nodes.remove(node);
        nodes.add(node);
    }

    public Pair get(int key) {
        Node node = keys.get(key);
        if (node == null) {
            return null;
        }
        nodes.remove(node);
        nodes.add(node);
        return new Pair(key, node.value);
    }

    private void evict() {
        keys.remove(nodes.tail.key);
        nodes.remove(nodes.tail);
    }
}


