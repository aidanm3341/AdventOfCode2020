package days.day7Attempt2;

import java.util.List;
import java.util.Map.Entry;

public class BagNode{
    String colour;
    List<Entry<BagNode, Integer>> children;

    public BagNode(String colour, List<Entry<BagNode, Integer>> children) {
        this.colour = colour;
        this.children = children;
    }

    public void accept(Visitor v){
        v.visit(this);
    }
}
