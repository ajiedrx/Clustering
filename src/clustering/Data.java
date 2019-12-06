package clustering;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.StringTokenizer;

/**
 *
 * @author Ajie DR
 */
public class Data {
    ArrayList<Coordinate> data = new ArrayList<>();
    ArrayList<Coordinate> centroids = new ArrayList<>();
    ArrayList<Coordinate> newCentroids = new ArrayList<>();
    int k = 0;
    
    public void readFile(String fileName) throws IOException {
        int[] dataTemp = new int[225];
        int[][] dataSet = new int[75][3];
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        StringBuilder sb = new StringBuilder();
        try {
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(",");
                line = br.readLine();
            }
            StringTokenizer st = new StringTokenizer(sb.toString(),",");
            for(int i=0;i<dataTemp.length;i++){
                dataTemp[i] = Integer.parseInt(st.nextToken().replaceAll("\n",""));
            }
            for (int i = 0, k=0; i < 75; i++)
                for (int jl = 0; jl < 3; jl++)
                    dataSet[i][jl] = dataTemp[k++];
         } finally {
            br.close();
        }
        for(int i = 0; i < dataSet.length; i++){
            data.add(new Coordinate(dataSet[i][0],dataSet[i][1]));        
        }
    }
    
    
    public void randomCentroids(int x){
        int max = 156;
        int min = 0;
        Random rand = new Random();
        for(int i = 0; i < x; i++){
            double a = rand.nextInt((max - min) +1 ) + min;
            double b = rand.nextInt((max - min) +1 ) + min;
            centroids.add(new Coordinate(a,b,i+1));
        }
        k = x;
    }
    
    public double getDistance(double x, double y, double a, double b){
        return Math.sqrt(((x - a)*(x - a))+((y - b)*(y - b)));
    }
    
    public void getCluster(){
        for(int i = 0; i < data.size(); i++){
            ArrayList<Double> distances = new ArrayList<>();
            for(int j = 0; j<centroids.size(); j++){
               double x = data.get(i).getX();
               double y = data.get(i).getY();
               double a = centroids.get(j).getX();
               double b = centroids.get(j).getY();
               distances.add(getDistance(x,y,a,b));
            }
            for(int w = 0; w < distances.size(); w++){
                if(Objects.equals(Collections.min(distances), distances.get(w))){
                    data.get(i).setZ(w+1);
                }
            }
//            data.get(i).setZ(distances.indexOf(Collections.min(distances)) + 1);
            distances.clear();
        }
    }
            
    public void getNewCentroids(){
        int[] counter = new int[centroids.size()];
        double[] sumx= new double[centroids.size()];
        double[] sumy= new double[centroids.size()];

        for(int i = 0; i < data.size(); i++){
            for(int j = 0; j < centroids.size(); j++){
                if(data.get(i).getZ() == j+1){
                    counter[j]++;
                    sumx[j] += data.get(i).getX();
                    sumy[j] += data.get(i).getY();
                    }
                }
        }
        
        for(int i = 0; i < centroids.size(); i++){
            if(counter[i] == 0){
                newCentroids.add(centroids.get(i));
            }
            else
                newCentroids.add(new Coordinate(sumx[i]/counter[i], sumy[i]/counter[i], centroids.get(i).getZ()));
        }
    }
    
    public boolean sameCentroid(){
        boolean status = true;
         for(int i = 0; i < centroids.size(); i++){
            if(centroids.get(i).getX() != newCentroids.get(i).getX() || 
                    centroids.get(i).getY() != newCentroids.get(i).getY()){
                status = false;
            }
        }
        return status;
    }
    
    public void checkCentroids(){
        while(!sameCentroid()){
            centroids.clear();
            centroids.addAll(newCentroids);
            newCentroids.clear();
            getCluster();
            getNewCentroids();
        };
    }
    
    public ArrayList getDataCoordinate(){
        return data;
    }
    
    public ArrayList getCentroidsCoordinate(){
        return centroids;
    }
    
    public int getK(){
        return k;
    }
 }
