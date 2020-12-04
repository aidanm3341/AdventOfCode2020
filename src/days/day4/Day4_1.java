package days.day4;

import utils.InputReader;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day4_1 {

    private static Map<String, String> parsePassport(Stream<String> p){
        Map<String, String> output = new HashMap<>();
        p.forEach(s -> {
            for (String pair : s.split(" "))
                output.put(pair.substring(0, 3), pair.substring(5));
        });
        return output;
    }

    private static boolean isValidPassport(Map<String, String> passport){
        Set<String> requiredFields = Set.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");

        Set<String> fields = passport.keySet();
        return fields.containsAll(requiredFields);
    }


    public static void main(String[] args) throws FileNotFoundException {
        Stream<String> inputs = new InputReader().readAsStream("4.txt");

        int numberOfValidPassports = 0;
        List<String> lines = new ArrayList<>();
        for(String line : inputs.collect(Collectors.toList())) {
            if(!line.equals("")){
                lines.add(line);
            }
            else{
                if(isValidPassport(parsePassport(lines.stream())))
                    numberOfValidPassports++;
                lines.clear();
            }
        }
        if(isValidPassport(parsePassport(lines.stream())))
            numberOfValidPassports++;
        System.out.println(numberOfValidPassports);
    }
}
