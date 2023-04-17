import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Deque;


public class BasicGraph<V> { 
   
    // Keep an index from node labels to nodes in the map
    protected Map<V, Vertex<V>> vertices; 

    /**
     * Construct an empty Graph.
     */
    public BasicGraph() {
       vertices = new HashMap<V, Vertex<V>>();
    }

    
    public void addVertex(V u) {
        addVertex(new Vertex<>(u));
    }
    
    public void addVertex(Vertex<V> v) {
        if (vertices.containsKey(v.name)) 
            throw new IllegalArgumentException("Cannot create new vertex with existing name.");
        vertices.put(v.name, v);
    }

    /**
     * Add a new edge from u to v.
     * Create new nodes if these nodes don't exist yet. 
     * This method permits adding multiple edges between the same nodes. 
     * @u unique name of the first vertex.
     * @w unique name of the second vertex.
     */
    public void addEdge(V u, V w, Integer cost) {
        if (!vertices.containsKey(u))
            addVertex(u);
        if (!vertices.containsKey(w))
            addVertex(w);

        Vertex<V> uvertex = vertices.get(u);
        uvertex.adjacent.add(
            new Edge<V>(uvertex, vertices.get(w), cost)); 



    }

    public void addEdge(V u, V w){
        addEdge(u,w,1);
    }

    public void printAdjacencyList() {
        for (V u : vertices.keySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append(u.toString());
            sb.append(" -> [ ");
            for (Edge e : vertices.get(u).adjacent){
                sb.append(e.target.name);
                sb.append(" ");
            }
            sb.append("]");
            System.out.println(sb.toString());
        }
    }    


    protected class Edge<V> {
        Vertex<V> source;
        Vertex<V> target;
        Integer cost;

        public Edge(Vertex<V> source, Vertex<V> target, Integer cost){
            this.source = source;
            this.target = target;
            this.cost = cost;
        }

    }

    protected class Vertex<V> {
        public V name;
        private List<Edge<V>> adjacent;
        int indegree; 
        double cost; // length of shortest incoming path
        Vertex<V> prev; 

        /**
         * Construct a new vertex containing an adjacency list.
         * @param vertexName a unique identifier for this vertex.
         */
        public Vertex(V vertexName){
            name = vertexName;
            adjacent = new LinkedList<Edge<V>>(); 
            indegree = 0; 
            cost = Double.POSITIVE_INFINITY;
        }

        public String toString() {
            return name.toString(); //+ "," + cost; 
        }

    }

    public void computeIndegrees() {

    }

    public List<V> topo_sort() {
      return null;
    }

    public void bfs_shortest_path(V start) {

    } 


    public void printVertices() {
    }

   
    public static void main(String[] args) {
        BasicGraph<String> g = new BasicGraph<>();
        g.addEdge("v1","v2");
        g.addEdge("v1","v3");
        g.addEdge("v2","v4");
        g.addEdge("v3","v4");
        g.addEdge("v3","v5");
        g.addEdge("v5","v6");
        g.addEdge("v4","v6");

        g.printAdjacencyList();
        System.out.println(g.topo_sort());

    } 

}
