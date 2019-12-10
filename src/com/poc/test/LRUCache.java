package com.poc.test;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private static Map<Integer, Node> cache;
    private Node head;
    private Node tail;
    int size;

    LRUCache(int size) {
        this.size = size;
        cache = new HashMap<>();
    }

    public int get(int key) {
        Node t = null;
        if (cache.containsKey(key)) {
            t = cache.get(key);
            removeNode(t);
            offerNode(t);
        }
        return (t != null ? t.val : -1);
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node t = cache.get(key);
            t.val = value;
            removeNode(t);
            offerNode(t);
        } else {
            if (cache.size() >= size) {
                cache.remove(key);
                removeNode(head);
            }
            Node newNode = new Node(value);
            offerNode(newNode);
            cache.put(key, newNode);
        }
    }

    private void removeNode(Node t) {
        if (t.prev != null) {
            t.prev.next = t.next;
        } else {
            head = t.next;
        }

        if (t.next != null) {
            t.next.prev = t.prev;
        } else {
            tail = t.prev;
        }
    }

    private void offerNode(Node t) {
        if (tail != null) {
            tail.next = t;
        }

        t.prev = tail;
        t.next = null;
        tail = t;

        if (head == null) {
            head = tail;
        }
    }

    static class Node {
        int val;
        Node prev;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }
}
