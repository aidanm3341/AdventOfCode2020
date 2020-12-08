package days.day7Attempt2;

import java.util.Map.Entry;

public abstract class BasicVisitor implements Visitor {
    @Override
    public void visit(BagNode n) {
        operation(n);

        for(Entry<BagNode, Integer> e : n.children)
            e.getKey().accept(this);
    }

    protected abstract void operation(BagNode n);
}
