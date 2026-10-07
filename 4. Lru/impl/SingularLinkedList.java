package impl;

public class SingularLinkedList {
    Node head;
    Node tail;


    public SingularLinkedList() {
        head = null;
        tail = null;
    }

    void add(Node node) {
        if (tail == null) {
            head = node;
            tail = node;
            return;
        }

        node.next = head;
        head.prev = node;
        head = node;

    }

    void moveToHead(Node node) {
        remove(node);
        add(node);
    }

    void remove(Node node) {
        if (node.prev != null)
            node.prev.next = node.next;

        if (node.next != null)
            node.next.prev = node.prev;

        if (node == tail) {
            tail = tail.prev;
        }
        if (node == head) {
            head = head.next;
        }
        node.next = null;
        node.prev = null;
    }
}
