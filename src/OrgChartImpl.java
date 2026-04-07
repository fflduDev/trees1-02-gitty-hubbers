// making a change
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class OrgChartImpl implements OrgChart {

    private List<GenericTreeNode<Employee>> nodes = new ArrayList<>();
    private GenericTreeNode<Employee> root;

    @Override
    public void addRoot(Employee e) {
        root = new GenericTreeNode<Employee>(e);
        nodes.add(root);
    }

    @Override
    public void clear() {
        nodes.clear();
        root = null;
    }

    @Override
    public void addDirectReport(Employee manager, Employee newPerson) {
        for (GenericTreeNode<Employee> currentEmployee : nodes) {
            if (currentEmployee.data.equals(manager)) {
                GenericTreeNode<Employee> newE = new GenericTreeNode<Employee>(newPerson);
                currentEmployee.addChild(newE);
                nodes.add(newE);
                break;
            }
        }
    }

    @Override
    public void removeEmployee(Employee firedPerson) {

        if (root == null) return;

        if (root.data.equals(firedPerson)) {
            clear();
            return;
        }

        GenericTreeNode<Employee> target = null;
        GenericTreeNode<Employee> parent = null;

        for (GenericTreeNode<Employee> node : nodes) {
            for (GenericTreeNode<Employee> child : node.children) {
                if (child.data.equals(firedPerson)) {
                    target = child;
                    parent = node;
                    break;
                }
            }
        }

        if (target == null) return;

        for (GenericTreeNode<Employee> child : target.children) {
            parent.addChild(child);
        }

        parent.children.remove(target);
        nodes.remove(target);
    }

    @Override
    public void showOrgChartDepthFirst() {
        if (root == null) return;

        Stack<GenericTreeNode<Employee>> stack = new Stack<>();
        stack.push(root);

        while (!stack.empty()) {
            GenericTreeNode<Employee> currentNode = stack.pop();
            System.out.println(currentNode.data);

            ArrayList<GenericTreeNode<Employee>> children = currentNode.children;

            for (int i = children.size() - 1; i >= 0; i--) {
                stack.push(children.get(i));
            }
        }
    }

    @Override
    public void showOrgChartBreadthFirst() {
        if (root == null) return;

        Queue<GenericTreeNode<Employee>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            GenericTreeNode<Employee> current = queue.remove();
            System.out.println(current.data);

            for (GenericTreeNode<Employee> child : current.children) {
                queue.add(child);
            }
        }
    }
}