package days;

import utils.InputReader;

import java.io.FileNotFoundException;
import java.util.List;

public class Day3_2 {
    public static void main(String[] args) throws FileNotFoundException {
        Map map = new Map(new InputReader().readAsList("3.txt"));

        System.out.println(
                calcTreeCountForSlope(map, 1, 1) *
                calcTreeCountForSlope(map, 3, 1) *
                calcTreeCountForSlope(map, 5, 1) *
                calcTreeCountForSlope(map, 7, 1) *
                calcTreeCountForSlope(map, 1, 2)
        );
    }

    private static long calcTreeCountForSlope(Map map, int p, int q){
        map.reset();
        int treeCount = 0;
        for(int i=0; i<map.getHeight(); i++){
            map.goRight(p);
            map.goDown(q);
            if(map.isTreeAtCurrentPos())
                treeCount++;
        }
        return treeCount;
    }

    private static class Map{
        private int x, y;
        private final List<String> map;

        public Map(List<String> map){
            this.map = map;
            reset();
        }

        public void goRight(int i){
            x += i;
        }
        public void goDown(int i){
            y += i;
        }

        public boolean isTreeAtCurrentPos(){
            if(y >= getHeight())
                return false;
            return map.get(y).charAt(x % getTileWidth()) == '#';
        }

        private int getTileWidth(){
            return map.get(0).length();
        }

        public int getHeight(){
            return map.size();
        }

        public void reset(){
            x = 0;
            y = 0;
        }
    }
}
