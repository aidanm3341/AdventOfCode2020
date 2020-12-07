package days;

import utils.InputReader;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Stream;

public class Day7_2 {
    private static Map<String, List<BagAmount>> getBagMappings(Stream<String> inputs){
        Map<String, List<BagAmount>> bagMapping = new HashMap<>();
        inputs.forEach(s -> {
            String colour = s.substring(0, s.indexOf(' ', s.indexOf(' ')+1));

            List<BagAmount> subColours = new ArrayList<>();

            if(!s.contains("no other bags"))
                for(String bag : s.substring(s.indexOf("contain")+7).split(","))
                    subColours.add(new BagAmount(Integer.parseInt(bag.charAt(1)+""),
                            bag.substring(3, bag.indexOf(' ', bag.indexOf(' ', 3) + 1))));

            bagMapping.put(colour, subColours);
        });
        return bagMapping;
    }

    private static int countBagsInBag(Map.Entry<String, List<BagAmount>> entry, Map<String, List<BagAmount>> mapping){
        int total = entry.getValue().stream().mapToInt(ba -> ba.amount).sum();
        for(BagAmount ba : entry.getValue())
            total += ba.amount * countBagsInBag(new AbstractMap.SimpleEntry<>(ba.bagColour, mapping.get(ba.bagColour)), mapping);
        return total;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Map<String, List<BagAmount>> bagMappings = getBagMappings(new InputReader().readAsStream("7.txt"));
        String target = "shiny gold";
        System.out.println(
                countBagsInBag(new AbstractMap.SimpleEntry<>(target, bagMappings.get(target)), bagMappings)
        );
    }

    private static class BagAmount{
        int amount;
        String bagColour;

        public BagAmount(int amount, String bagColour) {
            this.amount = amount;
            this.bagColour = bagColour;
        }
    }
}
