package days.day4;

import utils.InputReader;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day4_2 {

    private static PassportCandidate parsePassport(String p){
        Set<String> requiredFields = Set.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");

        Map<String, String> fields = new HashMap<>();
        for (String segment : p.split(" ")) {
            String[] pairs = segment.split(":");
            fields.put(pairs[0], pairs[1]);
        }

        if(fields.keySet().containsAll(requiredFields))
            return new Passport(fields.get("byr"),
                    fields.get("iyr"),
                    fields.get("eyr"),
                    fields.get("hgt"),
                    fields.get("hcl"),
                    fields.get("ecl"),
                    fields.get("pid"));
        else
            return new InvalidPassport();
    }

    public static void main(String[] args) throws FileNotFoundException {
        Stream<String> inputs = new InputReader().readAsStream("4.txt");

        int numberOfValidPassports = 0;
        List<String> lines = new ArrayList<>();
        for(String line : inputs.collect(Collectors.toList())) {
            if(!line.equals(""))
                lines.add(line);
            else{
                String passportLine = lines.stream().map(s -> s + " ").collect(Collectors.joining());
                if(parsePassport(passportLine).isValidPassport())
                    numberOfValidPassports++;
                lines.clear();
            }
        }

        String passportLine = lines.stream().map(s -> s + " ").collect(Collectors.joining());
        if(parsePassport(passportLine).isValidPassport())
            numberOfValidPassports++;

        System.out.println(numberOfValidPassports);
    }
}
