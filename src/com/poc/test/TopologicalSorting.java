package com.poc.test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class TopologicalSorting {

    private int V; // number of vertices
    private List<Integer> adj[];

    public TopologicalSorting(int v) {
        this.V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < V; i++)
            adj[i] = new LinkedList<>();
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    void topologicalSort() {
        Stack stack = new Stack();
        boolean[] visited = new boolean[V];

        for(int i = 0; i < V; i++)
            visited[i] = false;

        for(int i = 0; i < V; i++)
            if(!visited[i])
                topologicalSortUtil(i, visited, stack);

        while (stack.empty()==false)
            System.out.print(stack.pop() + " ");
    }

    void topologicalSortUtil(int v, boolean visited[], Stack stack) {
        visited[v] = true;
        Integer i;

        Iterator<Integer> it = adj[v].iterator();
        while(it.hasNext()) {
            i = it.next();
            if (!visited[i])
                topologicalSortUtil(i, visited, stack);
        }

        stack.push(v);
    }

    public static void main(String args[]) {
        TopologicalSorting t = new TopologicalSorting(4);
        t.addEdge(3, 1);
        t.addEdge(3, 2);
        t.addEdge(1, 0);
        t.addEdge(2, 0);

        t.topologicalSort();
    }

}
