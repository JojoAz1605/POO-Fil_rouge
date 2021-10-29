package solvers.executables;

import solverstests.RandomSchedulerTests;
import solverstests.TopologicalSorterTests;
import solverstests.VerifierTests;

public class Test {
    public static void main(String[] args) {
        boolean ok = true;
        TopologicalSorterTests tester = new TopologicalSorterTests();
        ok = ok && tester.testBruteForceSort();
        ok = ok && tester.testSchedule();
        ok = ok && tester.testLinearTimeSort();

        VerifierTests verifierTester = new VerifierTests();
        ok = ok && verifierTester.testUnsatisfied();

        // Without seed (normal runs)...
        RandomSchedulerTests randomSchedulerTester = new RandomSchedulerTests();
        // ... or with seed (for debugging)
        // RandomSchedulerTests randomSchedulerTester = new RandomSchedulerTests(1722117051601202760L);
        ok = ok && randomSchedulerTester.testGenerateSchedule();
        System.out.println(ok ? "All tests passed" : "At least one test failed");
    }
}
