package days.day7Attempt2;

import utils.InputReader;

import java.io.FileNotFoundException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

public class Day7 {
    private BagNode constructTree(List<String> inputs){
        List<Entry<BagNode, Integer>> children = new ArrayList<>();
        BagNode root = new BagNode("root", children);

        for(String s : inputs){
            String colour = s.substring(0, s.indexOf(' ', s.indexOf(' ')+1));
            List<String> childrenColours = parseChildrenColours(s);

            List<Entry<BagNode, Integer>> childrenBagNodes = new ArrayList<>();
            for(String t : childrenColours){
                BagNode ifExistChildren = searchForNode(t.substring(1), root);
                if(ifExistChildren == null)
                    ifExistChildren = new BagNode(t.substring(1), new ArrayList<>());

                childrenBagNodes.add(new AbstractMap.SimpleEntry<>(ifExistChildren, Integer.parseInt(t.charAt(0)+"")));
            }

            BagNode ifExists = searchForNode(colour, root);
            if(ifExists == null) {
                ifExists = new BagNode(colour, childrenBagNodes);
            }else{
                ifExists.children = childrenBagNodes;
            }
            children.add(new AbstractMap.SimpleEntry<>(ifExists, 0));
        }

        return root;
    }

    private List<String> parseChildrenColours(String input){
        List<String> childrenColours = new ArrayList<>();

        for(String s : input.substring(input.indexOf("contain")+"contain".length()).split(","))
            childrenColours.add(s.charAt(1) + s.substring(3, s.lastIndexOf(' ')));

        return childrenColours;
    }

    private BagNode searchForNode(String targetColour, BagNode root){
        if(root.colour.equals(targetColour))
            return root;
        else
            for(Entry<BagNode, Integer> e : root.children){
                BagNode n = searchForNode(e.getKey().colour, e.getKey());
                if(n != null)
                    return n;
            }
        return null;
    }

    public static void main(String[] args) throws FileNotFoundException {
        BagNode root = new Day7().constructTree(new InputReader().readAsList("7.txt"));
        new PrinterVisitor().visit(root);
    }
}
