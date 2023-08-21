package MavenSuiteFileCmdLine;

import org.testng.annotations.Test;

public class AppointmentCreation {

	@Test(groups = "smoke")
	public void createUser()
	{
		System.out.println("Appointment Created");
	}
	
	@Test(groups = "regression")
	public void deleteUser()
	{
		System.out.println("Appointment Deleted");
	}
}
