package Day3BinaryDiagnostic;

import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import common.Adventofile;

public class DiagnosticsDecoder{

    private static final char ZERO = '0';
    private static final char ONE = '1';

    private static BiMap map = new BiMap(
        new char[] {'0', '1'},
        new int[] {-1, 1}
    );

    public static void main(String[] args){
        List<String> data = Adventofile.asList("day3.txt");
        
        System.out.println(lifeSupportRating(data));
    }

    private static int powerConsumption(List<String> data){
        int[] occurances = new int[data.get(0).length()];

        for (String line: data){
            for (int i = 0; i < line.length(); i++){
                occurances[i] += map.get(line.charAt(i));
            }
        }


        return 
            Integer.parseInt(toBinary(occurances, (d) -> d > 0?1:0), 2) *
            Integer.parseInt(toBinary(occurances, (d) -> d < 0?1:0), 2);
    }

    private static int lifeSupportRating(List<String> data){
        int dataWidth = data.get(0).length();
        String oxygenRatingBinary = findRating(data, 0, dataWidth, ONE, (a, b) -> a - b);
        String co2RatingBinary = findRating(data, 0, dataWidth, ZERO, (a, b) -> b - a);

        return 
            Integer.parseInt(oxygenRatingBinary, 2) *
            Integer.parseInt(co2RatingBinary, 2);
    }
    
    private static String findRating(List<String> data, int currIndex, int stopIndex, char preference, Comparator<Integer> comparator){
        List<String> zero = new ArrayList<>();
        List<String> one = new ArrayList<>();

        for (String line: data){
            if (line.charAt(currIndex) == ZERO)
                zero.add(line);
            else
                one.add(line);
        }

        List<String> preferredList = findPreferredList(zero, one, preference, comparator);

        if (++currIndex < stopIndex && preferredList.size() > 1)
            return findRating(preferredList, currIndex, stopIndex, preference, comparator);
        else
            return preferredList.get(0);
    }

    private static List<String> findPreferredList(List<String> zero, List<String> one, char preference, Comparator<Integer> comparator){
        int comparison = comparator.compare(zero.size(), one.size());
        if (comparison > 0)
            return zero;
        else if (comparison < 0)
            return one;
        else if (preference == ZERO)
            return zero;
        else
            return one;

    }

    private static String toBinary(int[] data, Function<Integer, Integer> mapper){
        StringBuilder builder = new StringBuilder();
        for (int i: data){
            builder.append(mapper.apply(i));
        }
        return builder.toString();
    }

    private static class BiMap{
        private Map<Character, Integer> to = new HashMap<>();
        private Map<Integer, Character> from = new HashMap<>();
        BiMap(char[] from, int[] to){
            for (int i = 0; i < from.length; i++){
                this.to.put(from[i], to[i]);
                this.from.put(to[i], from[i]);
            }
        }
        int get(char key){
            return to.get(key);
        }
        int get(int key){
            return from.get(key);
        }
    }
}