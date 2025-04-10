// https://leetcode.com/problems/lru-cache/description/

import java.util.HashMap;
import java.util.Map;

class LRUCache {

    Node head, tail;
    Map<Integer, Node> map;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        Node node = map.get(key);
        moveToTop(node);
        return node.val;
    }

    public void moveToTop(Node node) {
        addToTop(disconnect(node));
    }

    public void addToTop(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    public Node disconnect(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        return node;
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            moveToTop(node);
            return;
        }
        Node node = new Node(key, value);
        map.put(key, node);
        addToTop(node);
        if (capacity <= 0) {
            map.remove(disconnect(tail.prev).key);
        }
        else {
            capacity--;
        }
    }
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(3);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        System.out.println(cache.get(1)); // returns 1

        cache.put(4, 4);
        System.out.println(cache.get(2)); // returns -1 (not found)
    }
}

class Node {
    int key, val;
    Node prev, next;
    public Node() {}
    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
}