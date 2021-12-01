package Day1SonarSweep;

import java.util.List;

import common.Adventofile;

public class Sonar{
    public static void main(String[] args){
        List<Integer> data = Adventofile.asMappedList("day1.txt", Integer::parseInt);
        
        System.out.println(windowedSteepness(data));
    }

    private static int steepness(List<Integer> data){
        int steepness = 0;

        for (int i = 1; i < data.size(); i++){
            if (data.get(i) > data.get(i-1)){
                steepness++;
            }
        }

        return steepness;
    }
    private static int windowedSteepness(List<Integer> data){
        int steepness = 0;
        int lastWindowSum = data.get(0) + data.get(1) + data.get(2);
    
        for (int i = 3; i < data.size(); i++){
            int windowSum = data.get(i) + data.get(i-1) + data.get(i-2); 
            if (windowSum > lastWindowSum){
                steepness++;
            }
            lastWindowSum = windowSum;
        }
        
        return steepness;
    }
}