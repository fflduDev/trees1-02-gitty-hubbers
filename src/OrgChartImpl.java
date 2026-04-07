

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

public class OrgChartImpl implements OrgChart{

	//Employee is your generic 'E'..
	private List<GenericTreeNode<Employee>> nodes = new ArrayList<>();
	
	
	@Override
	public void addRoot(Employee e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showOrgChartDepthFirst() {
		// create a stack used to do iterative DFS
       
              
        }
        }
		
	}

	@Override
	public void showOrgChartBreadthFirst() {
		// TODO Auto-generated method stub
		
	}
	
	
}