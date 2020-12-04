package days.day4;

import utils.InputReader;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day4_2 {

    private static PassportCandidate parsePassport(Stream<String> p){
        Set<String> requiredFields = Set.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");

        Map<String, String> output = new HashMap<>();
        p.forEach(s -> {
            for (String pair : s.split(" "))
                output.put(pair.substring(0, 3), pair.substring(4));
        });

        PassportCandidate pc;
        if(output.keySet().containsAll(requiredFields))
            pc = new Passport(output.get("byr"),
                    output.get("iyr"),
                    output.get("eyr"),
                    output.get("hgt"),
                    output.get("hcl"),
                    output.get("ecl"),
                    output.get("pid"));
        else
            pc = new InvalidPassport();
        return pc;
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
                if(parsePassport(lines.stream()).isValidPassport())
                    numberOfValidPassports++;
                lines.clear();
            }
        }
        if(parsePassport(lines.stream()).isValidPassport())
            numberOfValidPassports++;
        System.out.println(numberOfValidPassports);
    }
}
