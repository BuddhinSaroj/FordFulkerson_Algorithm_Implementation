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
}
