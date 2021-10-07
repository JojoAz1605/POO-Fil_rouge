package constraints.executables;

import constraintstests.ActivityTests;
import constraintstests.MeetConstraintTests;
import constraintstests.PrecedenceConstraintTests;
import constraintstests.PrecedenceConstraintWithGapTests;

public class Test {
    public static void main(String[] args) {
        boolean ok = true;

        // ----- test Activity -----
        ActivityTests activityTester = new ActivityTests();
        ok = ok && activityTester.testGetDescription();
        ok = ok && activityTester.testGetDuration();

        // ----- test PrecedenceConstraint -----
        PrecedenceConstraintTests precedenceConstraintTester = new PrecedenceConstraintTests();
        ok = ok && precedenceConstraintTester.testGetFirst();
        ok = ok && precedenceConstraintTester.testGetSecond();
        ok = ok && precedenceConstraintTester.testIsSatisfiedBy();
        System.out.println(ok ? "All tests passed" : "At least one test failed");

        // ----- test MeetConstraints -----
        MeetConstraintTests meetConstraintTester = new MeetConstraintTests();
        ok = ok && meetConstraintTester.testGetFirst();
        ok = ok && meetConstraintTester.testGetSecond();
        ok = ok && meetConstraintTester.testIsSatisfiedBy();

        System.out.println("------------------------------------------------");

        // ----- test PrecedenceConstraintWithGap -----
        PrecedenceConstraintWithGapTests precedenceConstraintWithGapTester = new PrecedenceConstraintWithGapTests();
        ok = ok && precedenceConstraintWithGapTester.testExtends();
        ok = ok && precedenceConstraintWithGapTester.testGetFirst();
        ok = ok && precedenceConstraintWithGapTester.testGetSecond();
        ok = ok && precedenceConstraintWithGapTester.testIsSatisfiedBy();

        System.out.println(ok ? "All tests passed" : "At least one test failed");
    }
}
