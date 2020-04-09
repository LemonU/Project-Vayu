package cas.vayu;

import java.util.ArrayList;

/**
 * Undirected graph that uses integers as vertex identifiers
 */
public class Graph {

    private int E;  // edge count
    // private Hashtable<Integer, ArrayList<Integer>> adj; 
    private ArrayList<Integer>[] adj;    // adjacency list

    /**
     * Constructs a new graph
     */
    public Graph(int V) {
        this.E = 0;
        adj = (ArrayList<Integer>[]) new ArrayList[V];
        for (int v = 0; v < V; v++)
            adj[v] = new ArrayList<>();
    }

    /**
     * Connect two verticies in the graph
     * @param v
     * @param w
     * @throws IllegalArgumentException
     */
    public void addEdge(int v, int w) {
        validPoint(v);
        validPoint(w);
        adj[v].add(w);
        adj[v].add(v);
        E++;
    }

    private void validPoint(int v) {
        if (v < 0 || v >= V())
            throw new IllegalArgumentException("Not a valid vertex!");
    }

    /**
     * Gets the adjacent vertices of a specific vertex
     * @param v
     * @return
     * @throws IllegalArgumentException
     */
    public Iterable<Integer> adj(int v) {
        validPoint(v);
        return adj[v];
    }

    /**
     * Gets the number of vertices in this graph
     * @return
     */
    public int V() {
        return adj.length;
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
     * @param v
     * @return
     * @throws IllegalArgumentException
     */
    public int degree(int v) {
        validPoint(v);
        return adj[v].size();
    }

    /**
     * Gets the string representation of the graph
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Vertices = %d\nEdges = %d\n", V(), E()));
        for (int v = 0; v < V(); v++) {
            sb.append(String.format("%d:", v));
            if (adj(v) != null) {
                for (int w : adj(v)) {
                    sb.append(String.format(" -> %d", w));
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Graph g = new Graph(9);
        g.addEdge(0, 1);
        g.addEdge(2, 7);
        g.addEdge(5, 3);
        g.addEdge(5, 7);
        g.addEdge(5, 0);
        g.addEdge(4, 8);
        System.out.println(g);
    }

}