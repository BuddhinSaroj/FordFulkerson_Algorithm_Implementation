public class Graph {

    //Adjacency matrix
    private int adjMatrix[][];
    private int numOfNodes;

    public Graph(int numOfNodes){
        this.numOfNodes = numOfNodes;
        adjMatrix = new int[numOfNodes][numOfNodes];
    }

    public int getNumOfNodes() {
        return numOfNodes;
    }

    public int[][] getAdjMatrix() {
        return adjMatrix;
    }
    public void addEdges(int startNode, int finishingNode, int capacity){
        adjMatrix[startNode][finishingNode] = capacity;
    }
    public int getCapacity(int startNode,int finishingNode){
        return adjMatrix[startNode][finishingNode];
    }

    public void printGraphs() {
        System.out.println("\nMatrix...");
        for (int i = 0; i < numOfNodes; i++) {
            for (int j = 0; j < numOfNodes; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
