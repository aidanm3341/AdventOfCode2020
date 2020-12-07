package days;

import utils.InputReader;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Stream;

public class Day7_1 {
    private static Map<String, List<String>> getBagMappings(Stream<String> inputs){
        Map<String, List<String>> bagMapping = new HashMap<>();
        inputs.forEach(s -> {
            String colour = s.substring(0, s.indexOf(' ', s.indexOf(' ')+1));

            List<String> subColours = new ArrayList<>();

            if(!s.contains("no other bags"))
                for(String bag : s.substring(s.indexOf("contain")+7).split(","))
                    subColours.add(bag.substring(3, bag.indexOf(' ', bag.indexOf(' ', 3) + 1)));

            bagMapping.put(colour, subColours);
        });
        return bagMapping;
    }

    private static boolean searchForString(String target, Map.Entry<String, List<String>> entry, Map<String, List<String>> mapping){
        if(entry.getValue().isEmpty()) return false;
        if(entry.getValue().contains(target)) return true;
        return entry.getValue().stream().anyMatch(s -> searchForString(
                target,
                new AbstractMap.SimpleEntry<>(s, mapping.get(s)),
                mapping)
        );
    }

    public static void main(String[] args) throws FileNotFoundException {
        Map<String, List<String>> bagMappings = getBagMappings(new InputReader().readAsStream("7.txt"));
        String target = "shiny gold";
        System.out.println(
                bagMappings.entrySet().stream().filter(entry -> searchForString(target, entry, bagMappings)).count()
        );
    }
}
