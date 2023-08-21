package GenericUtilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryImpClass implements IRetryAnalyzer{

	int count = 0;
	int retryCount = 3;
	public boolean retry(ITestResult result) {
		
		if(count<retryCount) {
			count++;
			return true;
		}
		return false;
	}

	
	
}
