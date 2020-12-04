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

    private static Stream<String> formatOnePassportPerLine(Stream<String> inputs){
        List<String> lines = new ArrayList<>();
        StringBuilder passportLine = new StringBuilder();

        for(String line : inputs.collect(Collectors.toList())) {
            if(!line.equals(""))
                passportLine.append(line).append(" ");
            else{
                lines.add(passportLine.toString());
                passportLine.setLength(0); // clearing the string builder without using the new keyword.
            }
        }
        lines.add(passportLine.toString()); // catch the last line.
        return lines.stream();
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(
                formatOnePassportPerLine(new InputReader().readAsStream("4.txt"))
                .filter(p -> parsePassport(p).isValidPassport())
                .count()
        );
    }
}
