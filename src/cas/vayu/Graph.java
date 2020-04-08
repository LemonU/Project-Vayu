package cas.vayu;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Undirected graph that uses integers as vertex identifiers
 */
public class Graph {

    private int E;  // edge count
    private Hashtable<Integer, ArrayList<Integer>> adj; // adjacency list

    /**
     * Constructs a new graph
     */
    public Graph() {
        this.E = 0;
        adj = new Hashtable<>();
    }

    /**
     * Connect two verticies in the graph
     * @param V
     * @param W
     */
    public void addEdge(int V, int W) {
        if (adj.get(V) == null)
            adj.put(V, new ArrayList<>());
        adj.get(V).add(W);
        if (adj.get(W) == null)
            adj.put(W, new ArrayList<>());
        adj.get(W).add(V);
        E++;
    }

    /**
     * Gets the adjacent vertices of a specific vertex
     * @param V
     * @return
     */
    public Iterable<Integer> adj(int V) {
        return adj.get(V);
    }

    /**
     * Gets all vertices in the graph
     * @return
     * An Iterable object containing all vertices
     */
    public Iterable<Integer> vertices() {
        return adj.keySet();
    }

    /**
     * Gets the number of vertices in this graph
     * @return
     */
    public int V() {
        return adj.size();
    }

    /**
     * Gets the number of edges in this graph
     * @return
     */
    public int E() {
        return E;
    }

    /**
     * Gets the number of edges of a specific vertex
     * @param V
     * @return
     */
    public int degree(int V) {
        return adj.get(V).size();
    }

    /**
     * Gets the string representation of the graph
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Vertices = %d\nEdges = %d\n", V(), E()));
        for (int V : adj.keySet()) {
            sb.append(String.format("%d:", V));
            if (adj(V) != null) {
                for (int w : adj(V)) {
                    sb.append(String.format(" -> %d", w));
                }
            }
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
        System.out.println(g);
    }

}