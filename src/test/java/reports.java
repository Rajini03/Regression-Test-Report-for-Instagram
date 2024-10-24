import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class reports implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {

        System.out.println("Test Started : " + result.getName());
    }


    @Override
    public void onTestSuccess(ITestResult  result) {

        System.out.println("Test Success : " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult  result) {

        System.out.println("Test Failed : " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult  result) {

        System.out.println("Test Skipped : " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {

        System.out.println("Starting Test Suit : " );
    }

    @Override
    public void onFinish(ITestContext context) {

        System.out.println("Finished Test Suit");
    }
}

