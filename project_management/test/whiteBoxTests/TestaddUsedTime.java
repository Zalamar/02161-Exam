package whiteBoxTests;

//Alex

import static org.junit.Assert.*;
import project_management.app.*;
import org.junit.Test;

public class TestaddUsedTime {
	
	Activity activity = new Activity("Test");
	Employee employee = new Employee("AAAA", "Alex");
	Employee employee2 = new Employee("AAAB", "Oliver");
	
	@Test
	public void testInput1() {
		activity.addUsedTime(10.0, "AAAA");
		assertEquals(10.0, activity.getUsedTime("AAAA"), 0.01);
	}
	
	@Test
	public void testInput2() {
		activity.addUsedTime(10.0, "AAAA");
		activity.addUsedTime(20.5, "AAAA");
		assertEquals(30.5, activity.getUsedTime("AAAA"), 0.01);
	}

	@Test
	public void testInput3() {
		activity.addUsedTime(-5.5, "AAAA");
		assertEquals(-5.5, activity.getUsedTime("AAAA"), 0.01);
	}
	
	@Test
	public void testInput4() {
		activity.addUsedTime(10.0, "AAAA");
		activity.addUsedTime(-2.0, "AAAA");
		assertEquals(8, activity.getUsedTime("AAAA"), 0.01);
	}
	
	@Test
	public void testInput5() {
		activity.addUsedTime(10.0, "AAAA");
		activity.addUsedTime(20.5, "AAAB");
		assertEquals(10, activity.getUsedTime("AAAA"), 0.01);
		assertEquals(20.5, activity.getUsedTime("AAAB"), 0.01);
	}
}
