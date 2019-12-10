package com.poc.test;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {

    Node root;
    List<Integer> list = new ArrayList<>();

    public static void main(String args[]) {

        BinaryTree binaryTree = new BinaryTree();

        binaryTree.add(1);


        //Node node = binaryTree.search(8);
        boolean isPresent = false;
        binaryTree.bfs(binaryTree.root, 2, isPresent);
        System.out.println(isPresent);
    }

    public void add(int value) {
        root = addRecursive(root, value);
    }

    private Node addRecursive(Node current, int value) {

        if (current == null) {
            return new Node(value);
        }

        if (value < current.value) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = addRecursive(current.right, value);
        } else {
            // value already exist
            return current;
        }
        return current;
    }

    public Node search(int value) {
        return containsNode(root, value);
    }

    private Node containsNode(Node node, int value) {
        if (node == null || node.value == value) return node;

        return value < node.value ? containsNode(node.left, value) : containsNode(node.right, value);
    }

    private void bfs(Node node, int k, boolean isPresent) {
        node.visited = true;
        list.add(node.value);
        if (list.contains(k - node.value)) {
            isPresent = true;
        }

        if (node.left != null) {
            bfs(node.left, k, isPresent);
        }
        if (node.right != null) {
            bfs(node.right, k, isPresent);
        }
    }

    static class Node {
        int value;
        Node left;
        Node right;
        boolean visited;

        Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
}


