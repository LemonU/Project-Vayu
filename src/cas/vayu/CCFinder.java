package cas.vayu;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;

/**
 * Connected Component finder for undirected graph with numerical vertex identifiers
 */
public class CCFinder {

    private int componentCount;    // # of existing components
    private Hashtable<Integer, Boolean> marked;    // records if a vertex has been explored
    private Hashtable<Integer, Integer> id;    // maps a vertex to its belonged component's id
    private Hashtable<Integer, ArrayList<Integer>> components;    // maps a component's id to its included vertices

    /**
     * Constructs a new Connected Component Finder
     * @param G
     * The input undirected graph in which the connected
     * components are to be found
     */
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

    /**
     * Checks if two vertices are in the same component
     * (i.e. a path exists between two vertices)
     * @param v The first vertex
     * @param w The second vertex
     * @return
     * Returns true if {@code v} and {@code w} belong to
     * the same component, false otherwise
     */
    public boolean connected(int v, int w) {
        return id.get(v) == id.get(w);
    }

    /**
     * Gets the number of existing connected components.
     * One may assume that all component id's are less than
     * this value.
     * @return
     * The number of existing connected components
     */
    public int componentCount() {
        return componentCount;
    }

    /**
     * Gets the id of the component that a vertex belongs to
     * @param v The vertex
     * @return
     * The id of the component that vertex {@code v} belongs to
     */
    public int componentIdOf(int v) {
        return id.get(v);
    }

    /**
     * Gets the size of the component that a vertex belongs to
     * @param v The vertex
     * @return
     * The size of the component that vertex {@code v} belongs to
     */
    public int componentSizeOf(int v) {
        return components.get(id.get(v)).size();
    }

    /**
     * Gets all vertices that are in the same component as a specifed
     * vertex.
     * Notice: the specifed vertex itself will be included as the result.
     * @param v The vertex used to find all other connected vertices
     * @return
     * And iterable object containing all vertices connected with {@code v}
     */
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