import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReading {

    public static void readFiles() throws FileNotFoundException {
        MaxFlow maxFlow = new MaxFlow();
        File file = new File("ladder_9.txt");
        System.out.println("Reading.....");
        System.out.println("File : "+file);
        Scanner scanner = new Scanner(file);
        String[] capArray = scanner.nextLine().split(" ");
        maxFlow.V = Integer.parseInt(capArray[0]);
        maxFlow.data = new int[maxFlow.V][maxFlow.V];

        while (scanner.hasNextLine()){
            String[] array = scanner.nextLine().split(" ");
            int startNode = Integer.parseInt(array[0]);
            int finishingNode = Integer.parseInt(array[1]);
            int capacity = Integer.parseInt(array[2]);
            maxFlow.data[startNode][finishingNode] = capacity;
        }
    }
}
