package designByContract;

import static org.junit.Assert.*;
import org.junit.Test;
import project_management.app.Activity;
import project_management.app.Employee;

public class designByContractAddUsedTime {
	Activity activity = new Activity("Test");
	Employee employee = new Employee("AAAA", "Alex");
	double x = 10.0;
	@Test
	public void test() {
		// pre
		assert x!=0;
		double z = activity.getUsedTime("AAAA");
		activity.addUsedTime(x,"AAAA");
		// post
		assert(activity.getUsedTime("AAAA")==z+x);
	}

}