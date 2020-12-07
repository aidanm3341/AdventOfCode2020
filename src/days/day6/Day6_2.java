package days.day6;

import utils.InputReader;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day6_2 {
    private static int calcCountOfUniqueAnswersInGroup(Stream<String> group){
        List<String> lines = group.collect(Collectors.toList());
        List<Character> chars = lines.get(0).chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        List<Character> candidateChars = lines.get(0).chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        for(Character c : chars) {
            for (String line : lines) {
                if(!line.contains(c+""))
                    candidateChars.remove(c);
            }
        }
        return candidateChars.size();
    }

    private static List<Stream<String>> convertNewLineSeparatedInputToStreamList(Stream<String> inputs){
        List<Stream<String>> output = new ArrayList<>();

        List<String> tempList = new ArrayList<>();
        for(String line : inputs.collect(Collectors.toList())){
            if(!line.equals(""))
                tempList.add(line);
            else{
                output.add(tempList.stream());
                tempList = new ArrayList<>();
            }
        }
        return output;
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(convertNewLineSeparatedInputToStreamList(new InputReader().readAsStream("6.txt")).stream()
                .mapToInt(Day6_2::calcCountOfUniqueAnswersInGroup).sum());
//        System.out.println(calcCountOfUniqueAnswersInGroup(List.of("abc", "cba", "ca", "a").stream()));
    }
}
