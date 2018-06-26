import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import unit.tests.AccountServiceTest;

public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(AccountServiceTest.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }
} 