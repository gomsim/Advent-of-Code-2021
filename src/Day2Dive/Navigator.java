package Day2Dive;

import java.util.List;

import common.Adventofile;

public class Navigator {

    private static final String UP = "up";
    private static final String DOWN = "down";
    private static final String FORWARD = "forward";

    private static final int DIR = 0;
    private static final int DIST = 1;


    public static void main(String[] args){
        List<List<String>> data = Adventofile.asMappedList("day2.txt", (line) -> {
            String[] tokens = line.split(" ");
            return List.of(tokens[DIR], tokens[DIST]);
        });
        
        System.out.println(improvedPositionCalculation(data));
    }

    private static int multipliedPosition(List<List<String>> data){
        int hor = 0, ver = 0;
        for (List<String> movement: data){
            switch(movement.get(DIR)){
                case UP: ver -= Integer.parseInt(movement.get(DIST)); break;
                case DOWN: ver += Integer.parseInt(movement.get(DIST)); break;
                case FORWARD: hor += Integer.parseInt(movement.get(DIST)); break;
            }
        }
        return hor * ver;
    }

    private static int improvedPositionCalculation(List<List<String>> data){
        int hor = 0, ver = 0, aim = 0;
        for (List<String> movement: data){
            int horMov = 0;
            switch(movement.get(DIR)){
                case UP: aim -= Integer.parseInt(movement.get(DIST)); break;
                case DOWN: aim += Integer.parseInt(movement.get(DIST)); break;
                case FORWARD: hor += horMov = Integer.parseInt(movement.get(DIST)); break;
            }
            ver += horMov * aim;
        }
        return hor * ver;
    }

}
