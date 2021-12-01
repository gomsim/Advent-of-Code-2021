package common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Adventofile implements ListFeed{

    BufferedReader reader;
    String nextLine;

    public Adventofile(String file){
        try{
            reader = new BufferedReader(new FileReader("input/" + file));
            nextLine = reader.readLine();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public String next(){
        String line = "";
        try{
            line = nextLine;
            nextLine = reader.readLine();
        }catch(IOException e){
            e.printStackTrace();
        }
        return line;
    }
    public boolean hasNext(){
        return nextLine != null;
    }
    public void close(){
        try{
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static List<String> asList(String file){
        Adventofile adventofile = new Adventofile(file);
        List<String> list = new ArrayList<>();

        while(adventofile.hasNext()){
            list.add(adventofile.next());
        }
        adventofile.close();

        return list;
    }
    
    public static List<String> asGroupedList(String file){
        Adventofile adventofile = new Adventofile(file);
        List<String> list = new ArrayList<>();

        while(adventofile.hasNext()){
            String outLine = "";
            String inLine;
            while(adventofile.hasNext() && !(inLine = adventofile.next()).isEmpty()){
                outLine += inLine + " ";
            }
            list.add(outLine.trim());
        }

        return list;
    }
    
    public static <E> List<E> asMappedList(String file, Function<String,E> map){
        List<E> list = new ArrayList<>();

        for(String s: asList(file)){
            list.add(map.apply(s));
        }

        return list;
    }

    public static char[][] asCharMatrix(String file){
        List<String> list = asList(file);
        
        char[][] matrix = new char[list.get(0).length()][list.size()];
        for (int x = 0; x < list.get(0).length(); x++){
            for (int y = 0; y < list.size(); y++){
                matrix[x][y] = list.get(y).charAt(x);
            }   
        }

        return matrix;
    }
}