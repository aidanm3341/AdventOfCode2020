package days;

import utils.InputReader;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Day5 {

    private static int calculateSeatID(String s){
//        int rowsToAdd = 64;
//        int lboundRow = 0, uboundRow = 127;
//        for(int i=0; i<7; i++){
////            if(s.charAt(i) == 'F')
////                uboundRow -= rowsToAdd;
//            if(s.charAt(i) == 'B')
//                lboundRow += rowsToAdd;
//            rowsToAdd /= 2;
//        }
//
//        rowsToAdd = 4;
//        int lboundColumn = 0, uboundColumn = 7;
//        for(int i=7; i<10; i++){
////            if(s.charAt(i) == 'R')
////                uboundColumn -= rowsToAdd;
//            if(s.charAt(i) == 'L')
//                lboundColumn += rowsToAdd;
//            rowsToAdd /= 2;
//        }
//        return lboundRow * 8 + lboundColumn;
        Stream<Character> chars = s.chars().map(Day5::charToBinary).mapToObj(c -> (char) c);
        StringBuilder sb = new StringBuilder();
        chars.forEach(sb::append);
        int row = Integer.parseInt(sb.substring(0, 7), 2);
        int column = Integer.parseInt(sb.substring(7, 10), 2);
        return row * 8 + column;
    }

    private static char charToBinary(int c){
        return (c == 'B' || c == 'R') ? '1' : '0';
    }

    public static void main(String[] args) throws FileNotFoundException {
        Stream<String> inputs = new InputReader().readAsStream("5.txt");
//        System.out.println(inputs.map(Day5::calculateSeatID).mapToInt(Integer::intValue).max());
        int[] ints = inputs.map(Day5::calculateSeatID).mapToInt(Integer::intValue).sorted().toArray();
        for(int i=0; i<ints.length; i++){
            if(ints[i+1] != ints[i]+1)
                System.out.println(ints[i]+1);
        }

    }
}
