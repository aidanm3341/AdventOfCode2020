package days.day7Attempt2;

public class PrinterVisitor extends BasicVisitor{
    @Override
    protected void operation(BagNode n) {
        System.out.println(n.colour);
    }
}
