// Java program for implementation of Ford Fulkerson algorithm
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.*;
import java.util.LinkedList;
import java.util.Scanner;

class MaxFlow {

    static Graph graph;

    /* Returns true if there is a path from source 's' to
    sink 't' in residual graph. Also fills parent[] to
    store the path */

    boolean bfs(int rGraph[][], int s, int t, int parent[])
    {
        // Create a visited array and mark all vertices as
        // not visited
        boolean visited[] = new boolean[graph.getNumOfNodes()];
        for (int i = 0; i < graph.getNumOfNodes(); ++i)
            visited[i] = false;

        // Create a queue, enqueue source vertex and mark
        // source vertex as visited
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        parent[s] = -1;

        // Standard BFS Loop
        while (queue.size() != 0) {
            int u = queue.poll();

            for (int v = 0; v < graph.getNumOfNodes(); v++) {
                if (visited[v] == false
                        && rGraph[u][v] > 0) {
                    // If we find a connection to the sink
                    // node, then there is no point in BFS
                    // anymore We just have to set its parent
                    // and can return true
                    if (v == t) {
                        parent[v] = u;
                        return true;
                    }
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }

        // We didn't reach sink in BFS starting from source,
        // so return false
        return false;
    }

    // Returns tne maximum flow from s to t in the given
    // graph
    int fordFulkerson(int graph[][], int s, int t)
    {
        int u, v;

        // Create a residual graph and fill the residual
        // graph with given capacities in the original graph
        // as residual capacities in residual graph

        // Residual graph where rGraph[i][j] indicates
        // residual capacity of edge from i to j (if there
        // is an edge. If rGraph[i][j] is 0, then there is
        // not)

        int[][] rGraph = new int[MaxFlow.graph.getNumOfNodes()][MaxFlow.graph.getNumOfNodes()];

        for (u = 0; u < MaxFlow.graph.getNumOfNodes(); u++)
            for (v = 0; v < MaxFlow.graph.getNumOfNodes(); v++)
                rGraph[u][v] = graph[u][v];

        // This array is filled by BFS and to store path
        int parent[] = new int[MaxFlow.graph.getNumOfNodes()];

        int max_flow = 0; // There is no flow initially

        // Augment the flow while tere is path from source
        // to sink
        while (bfs(rGraph, s, t, parent)) {
            // Find minimum residual capacity of the edhes
            // along the path filled by BFS. Or we can say
            // find the maximum flow through the path found.
            int path_flow = Integer.MAX_VALUE;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                path_flow
                        = Math.min(path_flow, rGraph[u][v]);
            }

            // update residual capacities of the edges and
            // reverse edges along the path
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;
            }

            // Add path flow to overall flow
            max_flow += path_flow;
        }

        // Return the overall flow
        return max_flow;
    }

    // Driver program to test above functions
    public static void main(String[] args) throws java.lang.Exception
    {
        // Let us create a graph shown in the above example
        MaxFlow maxFlow = new MaxFlow();

        Stopwatch stopwatch = new Stopwatch();
        int maxFlowAvg = 0;
        float stopwatchAvg[]=new float[3];
        readFiles();

        for(int i = 0 ; i<3 ; i++){
            maxFlowAvg += maxFlow.fordFulkerson(graph.getAdjMatrix(), 0, graph.getNumOfNodes()-1);
            stopwatchAvg[i] += stopwatch.elapsedTime();
        }

        float firstRound = stopwatchAvg[0];
        float secondRound = stopwatchAvg[1] - stopwatchAvg[0];
        float thirdRound = stopwatchAvg[2] - stopwatchAvg[1];

        System.out.println("The maximum possible flow is " + maxFlowAvg/3);
        System.out.println("1st iteration time :" + firstRound + "\n2nd iteration time :" + secondRound + "\n3rd iteration time :"+ thirdRound);
        System.out.println("Average Time: "+stopwatch.elapsedTime()/3);
    }

    private static void readFiles() throws FileNotFoundException {
        File file = new File("ladder_9.txt");
        System.out.println("Reading.....");
        System.out.println("File : "+file);
        Scanner scanner = new Scanner(file);
        String[] capArray = scanner.nextLine().split(" ");
        int numOfNodes = Integer.parseInt(capArray[0]);
        graph = new Graph(numOfNodes);

        while (scanner.hasNextLine()){
            String[] array = scanner.nextLine().split(" ");
            int startNode = Integer.parseInt(array[0]);
            int finishingNode = Integer.parseInt(array[1]);
            int capacity = Integer.parseInt(array[2]);
            graph.addEdges(startNode,finishingNode,capacity);
        }
    }
}
