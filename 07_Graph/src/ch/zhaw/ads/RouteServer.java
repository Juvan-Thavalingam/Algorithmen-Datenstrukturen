package ch.zhaw.ads;

import java.util.*;

public class RouteServer implements CommandExecutor {
    protected Graph<DijkstraNode,Edge> graph = new AdjListGraph<DijkstraNode, Edge>(DijkstraNode.class,Edge.class);

    /**
    build the graph given a text file with the topology
    */
    public Graph<DijkstraNode, Edge> createGraph(String topo) throws Exception {
        String city[] = topo.split("\n");
        try{
            for (String str : city){
                String split[] = str.split(" ");
                graph.addNode(split[0]);
                graph.addNode(split[1]);
                graph.addEdge(split[0],split[1], Double.parseDouble(split[2]));
                graph.addEdge(split[1],split[0], Double.parseDouble(split[2]));
            }
        } catch (Throwable throwable) {
            System.out.println("Fail");
        }
        return graph;
    }


    /**
    apply the dijkstra algorithm
    */
    public void dijkstraRoute(Graph<DijkstraNode, Edge> graph, String from, String to) {
        graph.getNodes().forEach(dijkstraNode -> dijkstraNode.setDist(Double.MAX_VALUE));

        Queue<DijkstraNode> redNode = new PriorityQueue<>();
        DijkstraNode current = graph.findNode(from);
        current.setDist(0);
        redNode.add(current);

        while (!redNode.isEmpty()) {
            current = redNode.remove();
            if(current == graph.findNode(to)) return;
            current.setMark(true);
            for (Edge edge : current.getEdges()){
                DijkstraNode neighbour = (DijkstraNode) edge.getDest();
                if(!neighbour.getMark()){
                    double dist = current.dist + edge.getWeight();
                    if(dist < neighbour.getDist()){
                        neighbour.setDist(dist);
                        neighbour.prev = current;
                        redNode.remove(neighbour);
                        redNode.add(neighbour);
                    }
                }
            }
        }
    }

    /**
    find the route in the graph after applied dijkstra
    the route should be returned with the start town first
    */
    public List<DijkstraNode> getRoute(Graph<DijkstraNode, Edge> graph, String to) {
        List<DijkstraNode> route = new LinkedList<>();
        DijkstraNode town = graph.findNode(to);
        do {
            route.add(0, town);
            town = town.getPrev();
        } while (town != null);
        return route;
    }

    public String execute(String topo) throws Exception {
        Graph<DijkstraNode, Edge> graph = createGraph(topo);
        dijkstraRoute(graph, "Winterthur", "Lugano");
        List<DijkstraNode> route = getRoute(graph, "Lugano");
        // generate result string
        StringBuilder builder = new StringBuilder();
        for (DijkstraNode rt : route) builder.append(rt).append("\n");
        return builder.toString();
    }

    public static void main(String[] args)throws Exception {
        String swiss = "Winterthur Zürich 25\n" +
                    "Zürich Bern 126\n" +
                    "Zürich Genf 277\n" +
                    "Zürich Luzern 54\n" +
                    "Zürich Chur 121\n" +
                    "Zürich Berikon 16\n" +
                    "Bern Genf 155\n" +
                    "Genf Lugano 363\n" +
                    "Lugano Luzern 206\n" +
                    "Lugano Chur 152\n" +
                    "Chur Luzern 146\n" +
                    "Luzern Bern 97\n" +
                    "Bern Berikon 102\n" +
                    "Luzern Berikon 41\n";
        RouteServer server = new RouteServer();
        System.out.println(server.execute(swiss));
    }
}
