import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class OrgChartImpl implements OrgChart {

    // Employee is your generic 'E'
    private List<GenericTreeNode<Employee>> nodes = new ArrayList<>();
    private GenericTreeNode<Employee> root;

    @Override
    public void addRoot(Employee e) {
        // clear old tree if you only want one root total
        clear();

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
        GenericTreeNode<Employee> managerNode = findNode(manager);

        if (managerNode == null) {
            return;
        }

        GenericTreeNode<Employee> newEmployeeNode = new GenericTreeNode<Employee>(newPerson);
        managerNode.addChild(newEmployeeNode);
        nodes.add(newEmployeeNode);
    }

    @Override
    public void removeEmployee(Employee firedPerson) {
        if (root == null) {
            return;
        }

        // case 1: removing the root
        if (root.data.equals(firedPerson)) {
            if (root.children.isEmpty()) {
                clear();
                return;
            }

            GenericTreeNode<Employee> oldRoot = root;

            // promote first child to new root
            GenericTreeNode<Employee> newRoot = oldRoot.children.remove(0);

            // move remaining old root children under new root
            for (GenericTreeNode<Employee> child : oldRoot.children) {
                newRoot.addChild(child);
            }

            root = newRoot;
            nodes.remove(oldRoot);
            return;
        }

        // case 2: removing a non-root employee
        GenericTreeNode<Employee> supervisor = findSupervisor(firedPerson);
        GenericTreeNode<Employee> firedNode = findNode(firedPerson);

        if (supervisor == null || firedNode == null) {
            return;
        }

        // remove fired employee from supervisor's child list
        supervisor.removeChild(firedPerson);

        // reassign fired employee's children to supervisor
        for (GenericTreeNode<Employee> child : firedNode.children) {
            supervisor.addChild(child);
        }

        nodes.remove(firedNode);
    }

    @Override
    public void showOrgChartDepthFirst() {
        if (root == null) {
            return;
        }

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
        if (root == null) {
            return;
        }

        Queue<GenericTreeNode<Employee>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            GenericTreeNode<Employee> currentNode = queue.remove();
            System.out.println(currentNode.data);

            for (GenericTreeNode<Employee> child : currentNode.children) {
                queue.add(child);
            }
        }
    }

    private GenericTreeNode<Employee> findNode(Employee employee) {
        for (GenericTreeNode<Employee> currentNode : nodes) {
            if (currentNode.data.equals(employee)) {
                return currentNode;
            }
        }
        return null;
    }

    private GenericTreeNode<Employee> findSupervisor(Employee employee) {
        for (GenericTreeNode<Employee> currentNode : nodes) {
            for (GenericTreeNode<Employee> child : currentNode.children) {
                if (child.data.equals(employee)) {
                    return currentNode;
                }
            }
        }
        return null;
    }
}