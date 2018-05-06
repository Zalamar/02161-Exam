package designByContract;

import static org.junit.Assert.*;
import org.junit.Test;
import project_management.app.Activity;

public class designByContractAddUsedTime {
	Activity activity = new Activity("Test");
	@Test
	public void test() {
		activity.addUsedTime(10.0,"AAAA");
		fail("Not yet implemented");
		/* pre:
		   added time must be x<0
		   
		   post:
		   time = addUsedTime+time
		   time<=0
		*/
	}

}
