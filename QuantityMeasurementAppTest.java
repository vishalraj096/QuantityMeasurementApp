public class QuantityMeasurementAppTest {
    private static int passed = 0;
    private static int failed = 0;

    public static void testEquality_SameValue() {
        QuantityMeasurementApp.Feet oneFootA = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet oneFootB = new QuantityMeasurementApp.Feet(1.0);

        assertTrue(oneFootA.equals(oneFootB), "1.0 ft should equal 1.0 ft");
    }

    public static void testEquality_DifferentValue() {
        QuantityMeasurementApp.Feet oneFoot = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet twoFeet = new QuantityMeasurementApp.Feet(2.0);

        assertFalse(oneFoot.equals(twoFeet), "1.0 ft should not equal 2.0 ft");
    }

    public static void testEquality_NullComparison() {
        QuantityMeasurementApp.Feet value = new QuantityMeasurementApp.Feet(1.0);

        assertFalse(value.equals(null), "Value should not equal null");
    }

    public static void testEquality_NonNumericInput() {
        QuantityMeasurementApp.Feet value = new QuantityMeasurementApp.Feet(1.0);
        Object nonNumericInput = "1.0";

        assertFalse(value.equals(nonNumericInput), "Feet should not equal String input");
    }

    public static void testEquality_SameReference() {
        QuantityMeasurementApp.Feet value = new QuantityMeasurementApp.Feet(1.0);

        assertTrue(value.equals(value), "Object should be equal to itself");
    }

    public static void testEquality_Symmetric() {
        QuantityMeasurementApp.Feet a = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet b = new QuantityMeasurementApp.Feet(1.0);

        assertTrue(a.equals(b), "Symmetric check part 1 failed");
        assertTrue(b.equals(a), "Symmetric check part 2 failed");
    }

    public static void testEquality_Transitive() {
        QuantityMeasurementApp.Feet a = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet b = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet c = new QuantityMeasurementApp.Feet(1.0);

        assertTrue(a.equals(b), "Transitive check a==b failed");
        assertTrue(b.equals(c), "Transitive check b==c failed");
        assertTrue(a.equals(c), "Transitive check a==c failed");
    }

    public static void testEquality_Consistent() {
        QuantityMeasurementApp.Feet a = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet b = new QuantityMeasurementApp.Feet(1.0);

        assertTrue(a.equals(b), "Consistent check first call failed");
        assertTrue(a.equals(b), "Consistent check second call failed");
        assertTrue(a.equals(b), "Consistent check third call failed");
    }

    private static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }

    private static void assertFalse(boolean condition, String message) {
        assertTrue(!condition, message);
    }

    private static void runTest(String testName, Runnable test) {
        try {
            test.run();
            passed++;
            System.out.println("PASS: " + testName);
        } catch (AssertionError error) {
            failed++;
            System.out.println("FAIL: " + testName + " -> " + error.getMessage());
        } catch (Exception exception) {
            failed++;
            System.out.println("FAIL: " + testName + " -> Unexpected error: " + exception.getMessage());
        }
    }

    public static void main(String[] args) {
        runTest("testEquality_SameValue", QuantityMeasurementAppTest::testEquality_SameValue);
        runTest("testEquality_DifferentValue", QuantityMeasurementAppTest::testEquality_DifferentValue);
        runTest("testEquality_NullComparison", QuantityMeasurementAppTest::testEquality_NullComparison);
        runTest("testEquality_NonNumericInput", QuantityMeasurementAppTest::testEquality_NonNumericInput);
        runTest("testEquality_SameReference", QuantityMeasurementAppTest::testEquality_SameReference);
        runTest("testEquality_Symmetric", QuantityMeasurementAppTest::testEquality_Symmetric);
        runTest("testEquality_Transitive", QuantityMeasurementAppTest::testEquality_Transitive);
        runTest("testEquality_Consistent", QuantityMeasurementAppTest::testEquality_Consistent);

        System.out.println();
        System.out.println("Total: " + (passed + failed));
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);

        if (failed > 0) {
            System.exit(1);
        }
    }
}