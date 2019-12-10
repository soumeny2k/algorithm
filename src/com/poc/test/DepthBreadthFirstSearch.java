package com.poc.test;

import java.util.*;

public class DepthBreadthFirstSearch {

    public static void main(String args[]) {

        Node node6 = new Node(3);
        Node node4 = new Node(9);
        Node node8 = new Node(20);
        Node node3 = new Node(15);
        Node node5 = new Node(7);
        Node node7 = new Node(7);
        Node node9 = new Node(9);

        node6.addNeighbours(node4);
        node6.addNeighbours(node8);
        //node4.addNeighbours(node3);
        //node4.addNeighbours(node5);
        node8.addNeighbours(node3);
        node8.addNeighbours(node5);

        Set<Integer> dfs = new LinkedHashSet<>();

        //System.out.println(dfs(node6, dfs));

        node6.setVisited(false);
        node4.setVisited(false);
        node8.setVisited(false);
        node3.setVisited(false);
        node5.setVisited(false);
        node7.setVisited(false);
        node9.setVisited(false);

        Set<Integer> bfs = new LinkedHashSet<>();

        //System.out.println(bfs(node6, bfs));

        List<List<Integer>> list = new ArrayList<>();
        List<Integer> data = new ArrayList<>(1);
        data.add(node6.data);
        list.add(data);
        zigzag(node6, list);

        System.out.println(list);
    }

    static void zigzag(Node node, List<List<Integer>> list) {
        node.visited = true;
        List<Node> neighbours = node.neighbours;
        List<Integer> values = new ArrayList<>();
        if (neighbours.size() > 0)
            list.add(values);

        for (int i = 0; i < neighbours.size(); i++) {
            Node temp = neighbours.get(i);
            if (!temp.visited) {
                values.add(temp.data);
                zigzag(temp, list);
            }
        }
    }

    static Set<Integer> dfs(Node node, Set<Integer> result) {
        List<Node> neighbours = node.getNeighbours();
        node.setVisited(true);
        result.add(node.data);

        for (int i = 0; i < neighbours.size(); i++) {
            Node innerNode = neighbours.get(i);
            if (innerNode != null && !innerNode.isVisited()) {
                dfs(innerNode, result);
            }
        }
        return result;
    }

    static Set<Integer> bfs(Node node, Set<Integer> result) {
        if (node == null) return null;

        result.add(node.data);
        node.setVisited(true);

        List<Node> neighbours = node.getNeighbours();
        List<Node> bfsList = new ArrayList<>();
        for (int i = 0; i < neighbours.size(); i++) {
            Node innerNode = neighbours.get(i);
            if (innerNode != null && !innerNode.isVisited()) {
                result.add(innerNode.data);
                bfsList.add(innerNode);
            }
        }
        for (Node node1: bfsList) {
            bfs(node1, result);
        }
        return result;
    }


    static class Node {
        private int data;
        private boolean visited;
        private List<Node> neighbours = new ArrayList<>();

        Node(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public boolean isVisited() {
            return visited;
        }

        public void setVisited(boolean visited) {
            this.visited = visited;
        }

        public List<Node> getNeighbours() {
            return neighbours;
        }

        public void addNeighbours(Node node) {
            this.neighbours.add(node);
        }

        public void setNeighbours(List<Node> neighbours) {
            this.neighbours = neighbours;
        }
    }
}
