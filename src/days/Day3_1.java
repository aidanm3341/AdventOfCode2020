package days;

import utils.InputReader;

import java.io.FileNotFoundException;
import java.util.List;

public class Day3_1 {
    public static void main(String[] args) throws FileNotFoundException {
        Map map = new Map(new InputReader().readAsList("3.txt"));

        int treeCount = 0;
        for(int i=0; i<map.getHeight(); i++){
                map.goRight(3);
                map.goDown(1);
                if(map.isTreeAtCurrentPos())
                    treeCount++;
        }
        System.out.println(treeCount);
    }

    private static class Map{
        private int x, y;
        private final List<String> map;

        public Map(List<String> map){
            this.map = map;
            x = 0;
            y = 0;
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
    }
}
