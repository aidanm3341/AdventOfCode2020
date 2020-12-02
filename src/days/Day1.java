package days;

import utils.InputReader;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day1 {
    public static void main(String[] args) throws FileNotFoundException {
        Stream<String> input = new InputReader().readLines("1.txt");
        Stream<Integer> nums = input.map(Integer::parseInt);
        List<Integer> ints = input.map(Integer::parseInt).collect(Collectors.toList());

        for (int i : ints)
            for (int j : ints)
                for (int k : ints)
                    if(i + j + k == 2020)
                        System.out.println(i * j * k);
    }
}
