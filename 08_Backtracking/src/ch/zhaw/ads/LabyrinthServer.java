package ch.zhaw.ads;

import java.awt.*;

public class LabyrinthServer implements CommandExecutor {
    ServerGraphics g = new ServerGraphics();
    protected Graph<DijkstraNode,Edge> graph = new AdjListGraph<DijkstraNode, Edge>(DijkstraNode.class,Edge.class);

    public Graph<DijkstraNode, Edge> createGraph(String s) {
        String[]graphArray = s.split("\n");

        try{
            for (String str : graphArray){
                String[] split = str.split(" ");
                graph.addEdge(split[0],split[1], 0);
                graph.addEdge(split[1],split[0], 0);
            }
        } catch (Throwable throwable) {
            System.out.println("Fail");
        }
        return graph;
    }

    public void drawLabyrinth(Graph<DijkstraNode, Edge> graph) {
        g.setColor(Color.black);
        g.fillRect(0,0,1,1);
        g.setColor(Color.white);
        for(DijkstraNode node : graph.getNodes()){
            for (Edge edge : node.getEdges()){
                g.drawPath(node.getName(), edge.dest.getName(), false);
            }
        }

    }

    private boolean search(DijkstraNode current, DijkstraNode ziel) {
        current.setMark(true);
        if (current.equals(ziel)) return true;
        else {
            for (Edge edge : current.getEdges()) {
                DijkstraNode node = (DijkstraNode) edge.getDest();
                if (!(node.getMark()) && search(node, ziel)) {
                    node.setPrev(current);
                    return true;
                }
            }
        }
        current.setMark(false);
        return false;
    }

    // search and draw result
    public void drawRoute(Graph<DijkstraNode, Edge> graph, String startNode, String zielNode) {
        g.setColor(Color.red);
        DijkstraNode dest = graph.findNode(zielNode);
        DijkstraNode start = graph.findNode(startNode);

        if(search(start, dest)){
            DijkstraNode currentNode = dest;
            while(currentNode != null && currentNode.getPrev() != null){
                g.drawPath(currentNode.getName(), currentNode.getPrev().getName(), true);
                currentNode = currentNode.getPrev();
            }
        }
    }

    public String execute(String s) {
        Graph<DijkstraNode, Edge> graph;
        graph = createGraph(s);
        drawLabyrinth(graph);
        drawRoute(graph, "0-6", "3-0");
        return g.getTrace();
    }
}
