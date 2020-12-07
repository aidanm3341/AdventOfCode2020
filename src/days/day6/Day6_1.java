package days.day6;

import utils.InputReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day6_1 {
    private static int calcCountOfUniqueAnswersInGroup(String s){
        Map<Character, Integer> answerCount = new HashMap<>();
        Stream<Character> chars = s.chars().mapToObj(c -> (char) c);
        chars.forEach(c -> answerCount.put(c, answerCount.getOrDefault(c, 0) + 1));
        return answerCount.keySet().size();
    }

    private static Stream<String> formatOneGroupPerLine(Stream<String> inputs){
        List<String> lines = new ArrayList<>();
        StringBuilder theOneLine = new StringBuilder();

        for(String line : inputs.collect(Collectors.toList())) {
            if(!line.equals(""))
                theOneLine.append(line);
            else{
                lines.add(theOneLine.toString());
                theOneLine.setLength(0); // clearing the string builder without using the new keyword.
            }
        }
        lines.add(theOneLine.toString()); // catch the last line.
        return lines.stream();
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(
                formatOneGroupPerLine(new InputReader().readAsStream("6.txt"))
                .mapToInt(Day6_1::calcCountOfUniqueAnswersInGroup).sum()
        );
    }
}
