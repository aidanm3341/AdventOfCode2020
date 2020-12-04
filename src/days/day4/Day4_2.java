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

    public static boolean isValidPassport(Map<String, String> passport){
        Set<String> requiredFields = Set.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");

        Set<String> fields = passport.keySet();
        return fields.containsAll(requiredFields) &&
                isBirthYearValid(passport.get("byr")) &&
                isIssueYearValid(passport.get("iyr")) &&
                isExpirationYearValid(passport.get("eyr")) &&
                isHeightValid(passport.get("hgt")) &&
                isValidHairColour(passport.get("hcl")) &&
                isValidEyeColour(passport.get("ecl")) &&
                isValidPassportID(passport.get("pid"));
    }

    private static boolean isBirthYearValid(String s){
        if(s.length() != 4) return false;
        int val = Integer.parseInt(s);
        return val >= 1920 && val <= 2002;
    }

    private static boolean isIssueYearValid(String s){
        if(s.length() != 4) return false;
        int val = Integer.parseInt(s);
        return val >= 2010 && val <= 2020;
    }

    private static boolean isExpirationYearValid(String s){
        if(s.length() != 4) return false;
        int val = Integer.parseInt(s);
        return val >= 2020 && val <= 2030;
    }

    private static boolean isHeightValid(String s){
        if(s.endsWith("cm")){
            int val = Integer.parseInt(s.substring(0, s.length() - 2));
            return val >= 150 && val <= 193;
        }
        else if(s.endsWith("in")){
            int val = Integer.parseInt(s.substring(0, s.length() - 2));
            return val >= 59 && val <= 76;
        }
        else
            return false;
    }

    private static boolean isValidHairColour(String s){
        return s.matches("#[0-9a-f]{6}");
    }

    private static boolean isValidEyeColour(String s){
        Set<String> validColours = Set.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
        return validColours.contains(s);
    }

    private static boolean isValidPassportID(String s){
        return s.matches("[0-9]{9}");
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
