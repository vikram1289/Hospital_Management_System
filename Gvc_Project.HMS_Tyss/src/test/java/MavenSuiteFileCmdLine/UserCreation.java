package MavenSuiteFileCmdLine;

import org.testng.annotations.Test;

public class UserCreation {

	@Test(groups = "smoke")
	public void createUser()
	{
		System.out.println("User Created");
	}
	
	@Test(groups = "regression")
	public void deleteUser()
	{
		System.out.println("User Deleted");
	}
	
}
