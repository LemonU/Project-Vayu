package cas.vayu;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Connected Component finder for undirected graph with numerical vertex identifiers
 */
public class CCFinder {

    private int componentCount;    // # of existing components
    private boolean[] marked;    // records if a vertex has been explored
    private int[] id;    // maps a vertex to its belonged component's id
    private ArrayList<Integer>[] components;    // maps a component's id to its included vertices

    /**
     * Constructs a new Connected Component Finder
     * @param G
     * The input undirected graph in which the connected
     * components are to be found
     */
    public CCFinder(Graph G) {
        componentCount = 0;
        marked = new boolean[G.V()];
        id = new int[G.V()];
        components = (ArrayList<Integer>[]) new ArrayList[G.V()];
        for (int v = 0; v < G.V(); v++) {
            marked[v] = false;
            id[v] = 0;
            components[v] = new ArrayList<>();
        }

        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
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
            marked[v] = true;
            id[v] = componentCount;
            components[componentCount].add(v);
            for (int w : G.adj(v)) {
                if (!marked[w])
                    queue.enque(w);
            }
        }
        
    }

    /**
     * Checks if two vertices are in the same component
     * (i.e. a path exists between two vertices)
     * @param v The first vertex
     * @param w The second vertex
     * @throws IllegalArgumentException
     * @return
     * Returns true if {@code v} and {@code w} belong to
     * the same component, false otherwise
     */
    public boolean connected(int v, int w) {
        validPoint(v);
        validPoint(w);
        return id[v] == id[w];
    }

    private void validPoint(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("Not a valid vertex!");
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
     * @throws IllegalArgumentException
     * @return
     * The id of the component that vertex {@code v} belongs to
     */
    public int componentIdOf(int v) {
        validPoint(v);
        return id[v];
    }

    /**
     * Gets the size of the component that a vertex belongs to
     * @param v The vertex
     * @throws IllegalArgumentException
     * @return
     * The size of the component that vertex {@code v} belongs to
     */
    public int componentSizeOf(int v) {
        validPoint(v);
        return components[id[v]].size();
    }

    /**
     * Gets all vertices that are in the same component as a specifed
     * vertex.
     * Notice: the specifed vertex itself will be included as the result.
     * @param v The vertex used to find all other connected vertices
     * @throws IllegalArgumentException
     * @return
     * And iterable object containing all vertices connected with {@code v}
     */
    public Iterable<Integer> connectedVerticesOf(int v) {
        validPoint(v);
        return components[componentIdOf(v)];
    }
    
    /**
     * Gets a connected component by its id
     * @param id The id of the connected component
     * @throws IllegalArgumentException
     * @return
     * An iterable object cotaining all vertices of this component
     */
    public Iterable<Integer> getComponentById(int id) {
        if (id < 0 || id > componentCount())
            throw new IllegalArgumentException("Not a valid component ID!");
        return components[id];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Number of Components: %d\n", componentCount));
        for (int id = 0; id < componentCount(); id++) {
            sb.append(id + ":");
            for (int v : getComponentById(id))
                sb.append(" " + v);
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Graph g = new Graph(10);
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