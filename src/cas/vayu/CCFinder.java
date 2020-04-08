package cas.vayu;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;

public class CCFinder {

    private int componentCount;
    private Hashtable<Integer, Boolean> marked;
    private Hashtable<Integer, Integer> id;
    private Hashtable<Integer, ArrayList<Integer>> components;

    public CCFinder(Graph G) {
        componentCount = 0;
        marked = new Hashtable<>();
        for (int v : G.vertices())
            marked.put(v, false);
        id = new Hashtable<>();
        components = new Hashtable<>();
        for (int v : G.vertices()) {
            if (!marked.get(v)) {
                bfs(G, v);
                componentCount++;
            }
        }
    }

    private class Queue extends LinkedList<Integer>{
        void enque(int v) { this.add(v); }
        int dequeue() { return this.remove(0); }
    }

    private void bfs(Graph G, int s) {
        Queue queue = new Queue();
        queue.enque(s);

        while(!queue.isEmpty()) {
            int v = queue.dequeue();
            marked.put(v, true);
            id.put(v, componentCount);
            if (components.get(componentCount) == null)
                components.put(componentCount, new ArrayList<>());
            components.get(componentCount).add(v);
            for (int w : G.adj(v)) {
                if (!marked.get(w))
                    queue.enque(w);
            }
        }
        
    }

    public boolean connected(int v, int w) {
        return id.get(v) == id.get(w);
    }

    public int componentCount() {
        return componentCount;
    }

    public int componentIdOf(int v) {
        return id.get(v);
    }

    public int componentSizeOf(int v) {
        return components.get(id.get(v)).size();
    }

    public Iterable<Integer> connectedVerticesOf(int v) {
        return components.get(componentIdOf(v));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Number of Components: %d\n", componentCount));
        for (int id : components.keySet()) {
            sb.append(id + ":");
            for (int v : components.get(id))
                sb.append(" " + v);
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        g.addEdge(0, 1);
        g.addEdge(2, 7);
        g.addEdge(5, 3);
        g.addEdge(5, 7);
        g.addEdge(5, 0);
        g.addEdge(4, 8);
        CCFinder cc = new CCFinder(g);
        System.out.println(cc);
    }

}