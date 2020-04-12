package cas.vayu.graph;

import java.util.ArrayList;

/**
 * Undirected graph that uses integers as vertex identifiers. This
 * is an array-based implementation, thus the range of vertex identifiers
 * will be limited to correspond to the total number of nodes. This means
 * that one may assume all vertex identifiers lies within [0, V)
 */
public class Graph {

    private int V;    // number of vertices in this graph
    private int E;    // edge count
    private ArrayList<Integer>[] adj;    // adjacency list

    /**
     * Constructs a new graph
     * @param V The number of nodes in this graph
     */
    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = (ArrayList<Integer>[]) new ArrayList[V];
        for (int v = 0; v < V; v++)
            adj[v] = new ArrayList<>();
    }

    /**
     * Connect two verticies in the graph with a new
     * undirected graph.
     * Notice: Duplicate edges are permitted in this implementation
     * @param v First vertex
     * @param w Second vertex
     * @throws IllegalArgumentException
     * When either {@code v} or {@code w} is not a valid
     * vertex identifier
     */
    public void addEdge(int v, int w) {
        validPoint(v);
        validPoint(w);
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }
    /**
     * Checks if the passed integer vertex is in the graph. If it isn't
     * throws IllegalArgumentException.
     * @param v Vertex to check if existing in the graph
     * @throws IllegalArgumentException if the given vertex is less than 0 or
     * greater than the size of the graph
     */
    private void validPoint(int v) {
        if (v < 0 || v >= V())
            throw new IllegalArgumentException("Not a valid vertex!");
    }

    /**
     * Gets the adjacent vertices of a specific vertex
     * @param v
     * The vertex whose adjacent vertices are to be fetched
     * @return
     * An iterable object containing all adjacent vertices
     * of vertex {@code v}
     * @throws IllegalArgumentException
     * When {@code v} is not a valid vertex identifier
     */
    public Iterable<Integer> adj(int v) {
        validPoint(v);
        return adj[v];
    }

    /**
     * Gets the number of vertices in this graph
     * @return
     * The number of vertices in this graph
     */
    public int V() {
        return V;
    }

    /**
     * Gets the number of edges in this graph
     * @return
     * The number of edges in this graph
     */
    public int E() {
        return E;
    }

    /**
     * Gets the number of edges of a specific vertex
     * @param v
     * The specified vertex whose degree is to be returned
     * @return
     * The degree of vertex {@code v}
     * @throws IllegalArgumentException
     * When {code v} is not a valid vertex identifier
     */
    public int degree(int v) {
        validPoint(v);
        return adj[v].size();
    }

    /**
     * Gets the string representation of this graph
     * @return String with number of vertices and edges, followed by
     * each edge on a new line in the format "v1 -> v2"
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