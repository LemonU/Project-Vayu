package cas.vayu.graph;

import java.util.ArrayList;
import java.util.HashSet;

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
                dfs(G, v);
                componentCount++;
            }
        }
    }
    /**
     * Performs depth first search in the graph G starting from source s.
     * Adds all the explored points to a connected component
     * @param G Graph to search for connected nodes
     * @param s source of the depth first search in the graph
     */
    private void dfs(Graph G, int s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(s);

        while(!stack.isEmpty()) {
            int v = stack.pop();
            marked[v] = true;
            id[v] = componentCount;
            components.get(componentCount).add(v);
            for (int w : G.adj(v)) {
                if (!marked[w])
                    stack.push(w);
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

    /**
     * Checks if the passed integer vertex is in the graph. If it isn't
     * throws IllegalArgumentException.
     * @param v Vertex to check if existing in the graph
     * @throws IllegalArgumentException if the given vertex is less than 0 or
     * greater than the size of the graph
     */
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

    /**
     * String representation of CCFinder. Has the form:
     * "Number of Components: componentCount"
     * @return Returns the string representation of the CCFinder
     */
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

    /**
     * Main method used for unit testing the CCFinder
     * @param args Arguments passed to the main methods
     */
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