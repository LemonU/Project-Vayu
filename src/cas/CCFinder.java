package cas;

import java.util.ArrayList;
import java.util.Hashtable;

import cas.vayu.Graph;

public class CCFinder {

    private int componentCount;
    private Hashtable<Integer, ArrayList<Integer>> components;

    public CCFinder(Graph G) {
        componentCount = 0;
        components = new Hashtable<>();
        dfs(G);
    }

    private void dfs(Graph G) {

    }

    public boolean connected(int V, int W) {
        return false;
    }

    public int componentCount() {
        return componentCount;
    }

    public int componentId(int V) {
        return 0;
    }

    public int componentSize(int V) {
        return 0;
    }

    public Iterable<Integer> connectedVertices(int V) {
        return components.get(V);
    }

}