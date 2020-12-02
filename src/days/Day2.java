package days;

import utils.InputReader;

import java.io.FileNotFoundException;
import java.util.stream.Stream;

public class Day2 {

    private long numberOfValidPasswords(Stream<String> inputs){
        return inputs.filter(this::isValidPasswordPart2).count();
    }

    private boolean isValidPasswordPart1(String s){
        int lbound = Integer.parseInt(s.substring(0, s.indexOf('-')));
        int ubound = Integer.parseInt(s.substring(s.indexOf('-')+1, s.indexOf(' ')));

        char target = s.charAt(s.indexOf(":")-1);

        long countOfTarget = s.chars().filter(c -> c == target).count() - 1;
        return countOfTarget >= lbound && countOfTarget <= ubound;
    }

    private boolean isValidPasswordPart2(String s){
        int lindex = Integer.parseInt(s.substring(0, s.indexOf('-')));
        int uindex = Integer.parseInt(s.substring(s.indexOf('-')+1, s.indexOf(' ')));

        char target = s.charAt(s.indexOf(":")-1);

        s = s.substring(s.indexOf(":")+2);
        return s.charAt(lindex-1) == target ^ s.charAt(uindex-1) == target;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Stream<String> inputs = new InputReader().readLines("2.txt");
        System.out.println(new Day2().numberOfValidPasswords(inputs));
    }
}
