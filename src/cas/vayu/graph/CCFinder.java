package cas.vayu.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

import cas.vayu.disasterarea.builder.Stack;

/**
 * Connected Component finder for undirected graph with numerical vertex identifiers
 */
public class CCFinder {

    private int componentCount;    // number of existing components
    private boolean[] marked;    // records if a vertex has been explored
    private int[] id;    // maps a vertex to its belonged component's id
    private ArrayList<HashSet<Integer>> components;    // maps a component's id to its included vertices

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
        components = new ArrayList<>();
        for (int v = 0; v < G.V(); v++) {
            marked[v] = false;
            id[v] = 0;
        }

        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
            	HashSet<Integer> component = new HashSet<>();
            	components.add(component);
                bfs(G, v);
                componentCount++;
            }
        }
    }

    private void bfs(Graph G, int s) {
        Stack<Integer> queue = new Stack();
        queue.push(s);

        while(!queue.isEmpty()) {
            int v = queue.pop();
            marked[v] = true;
            id[v] = componentCount;
            components.get(componentCount).add(v);
            for (int w : G.adj(v)) {
                if (!marked[w])
                    queue.push(w);
            }
        }
        
    }


    /**
     * Checks if two vertices are in the same component
     * (i.e. a path exists between two vertices)
     * @param v The first vertex
     * @param w The second vertex
     * @throws IllegalArgumentException
     * When either {@code v} or {@code w} is not a valid
     * vertex identifier
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
     * Gets a connected component by its id
     * @param id The id of the connected component
     * @throws IllegalArgumentException
     * When {@code id} is not a valid component ID (i.e. liies outside the
     * range of [0, component count))
     * @return
     * An iterable object cotaining all vertices of this component
     */
    public HashSet<Integer> getComponentById(int id) {
        if (id < 0 || id > componentCount())
            throw new IllegalArgumentException("Not a valid component ID!");
        return components.get(id);
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