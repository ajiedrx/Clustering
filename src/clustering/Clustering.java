package clustering;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Ajie DR
 */
public class Clustering {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        Data data = new Data();
        ClusterAnalysis clusterAnalyze = new ClusterAnalysis();
        data.readFile("C:/ruspini.csv");
        System.out.println("Masukkan n : ");
        data.randomCentroids(input.nextInt());
        input.close();
        System.out.println("\nCentroids : ");
        for(int i = 0; i < data.centroids.size(); i++){
            System.out.println(data.centroids.get(i).getX() + " || " + data.centroids.get(i).getY());
        }
        data.getCluster();
        for(int i = 1; i < data.getK() + 1; i++){
            int counts = 0;
            System.out.println("\nData pada cluster ke-"+i);
            for(int j = 0; j<data.data.size(); j++){
                if(data.data.get(j).getZ() == i){
                    System.out.println(data.data.get(j).getX() + " || " + data.data.get(j).getY());
                    counts++;
                }
            }
            System.out.println("Jumlah data : "+counts);
        }
        data.getNewCentroids();
        System.out.println("\nNew Centroids : ");
        for(int i = 0; i < data.centroids.size(); i++){
            System.out.println(data.newCentroids.get(i).getX() + " || " + data.newCentroids.get(i).getY());
        }
       
        data.checkCentroids();
        System.out.println("\nLast Centroids : ");
        for(int i = 0; i < data.centroids.size(); i++){
            System.out.println(data.centroids.get(i).getX() + " || " + data.centroids.get(i).getY());
        }
        clusterAnalyze.SSE(data);
    }
}
