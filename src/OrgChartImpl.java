

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

public class OrgChartImpl implements OrgChart{

	//Employee is your generic 'E'..
	private List<GenericTreeNode<Employee>> nodes = new ArrayList<>();
	private GenericTreeNode<Employee> root;
	@Override
	public void addRoot(Employee e) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		for (int i = 0; i < nodes.size(); i++) {
			GenericTreeNode<Employee> currentEmployee = nodes.get(i);
			if (currentEmployee.data.equals(manager)) {
				GenericTreeNode<Employee> newE = new GenericTreeNode<Employee>(newPerson);
				
				//add child to the current employee's list of children
				//currentEmployee.addChild(new GenericTreeNode<Employee>(newPerson));c
				currentEmployee.addChild(newE);
				
				nodes.add(newE);
				break;
			}
		}
	}

	@Override
    public void removeEmployee(Employee firedPerson) {
        if (root == null) {
            return;
        }
        
        if (root.data.equals(firedPerson)) {
            if (root.children.isEmpty()) {
                clear();
                return;
            }
            
            GenericTreeNode<Employee> oldRoot = root;
            GenericTreeNode<Employee> newRoot = root.children.remove(0);
            for (GenericTreeNode<Employee> child : oldRoot.children) {
                newRoot.addChild(child);
            }
            root = newRoot;
            nodes.remove(oldRoot);
            return;
        }
        
        GenericTreeNode<Employee> supervisor = findSupervisor(firedPerson);
        GenericTreeNode<Employee> firedNode = findNode(firedPerson);
        
        if (supervisor == null || firedNode == null) {
            return;
        }
        
        supervisor.removeChild(firedPerson);
        nodes.remove(firedNode);
    }

<<<<<<< HEAD
	@Override
	public void showOrgChartDepthFirst() {
		// create a stack used to do iterative DFS
=======
    @Override
    public void showOrgChartDepthFirst() {
        if (root == null) {
            return;
        }
        
        // create a stack used to do iterative DFS
>>>>>>> 4ed60d5 (Final commit)
        Stack<GenericTreeNode<Employee>> stack = new Stack<>();
 
        // push the root into the stack
        stack.push(root);
<<<<<<< HEAD
 	    
=======
        
>>>>>>> 4ed60d5 (Final commit)
        // loop till stack is empty
        while (!stack.empty())
        {
            // Pop a node from the stack
            GenericTreeNode<Employee> currentNode = stack.pop();
            System.out.println(currentNode.data);
 
            ArrayList<GenericTreeNode<Employee>> children = currentNode.children;
            
            // Visit node
            // Push the children of node onto stack
<<<<<<< HEAD

            for (int i = children.size() - 1; i >= 0; i--)
            {
               stack.push(children.get(i));
              
        }
        }
		
	}
=======
>>>>>>> 4ed60d5 (Final commit)

            for (int i = children.size() - 1; i >= 0; i--)
            {
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