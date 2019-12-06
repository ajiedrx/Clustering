/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clustering;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Ajie DR
 */
public class ClusterAnalysis {
    Data data = new Data();
    ArrayList<Integer> clusters = new ArrayList<>();
    
    public int getClusterSum(int cluster){
        int count = 0;
        for(int i = 0; i < data.data.size(); i++){
            if(cluster == data.data.get(i).getZ()){
                count++;
            }
        }
        return count;
    }
    
    public double countDistance(double x, double y, double a, double b){
        double X,Y;
        X = Math.pow(( x - a), 2);
        Y = Math.pow(( y - b), 2);
        return X+Y;        
    }                                                                                                                      
            
    public double SSE(Data data){
        double sse = 0;
        for(int i = 1; i <= data.getK(); i++){
            double temp = 0;
            for(int j = 0; j < data.data.size(); j++){
                if(data.data.get(j).getZ() == i){
                    temp += countDistance(data.data.get(j).getX(), 
                            data.data.get(j).getY(), 
                            data.centroids.get(i-1).getX(), 
                            data.centroids.get(i-1).getY());
                }
            }
            sse += temp;
        }
        sse /= (double)data.data.size();
        System.out.println("\nCluster Analysis SSE : "+sse);
        return sse;
    }
}
