public class QuantityMeasurementAppTest {
    private static int passed = 0;
    private static int failed = 0;

    public static void testEquality_FeetToFeet_SameValue() {
        QuantityMeasurementApp.Length a = new QuantityMeasurementApp.Length(1.0, "feet");
        QuantityMeasurementApp.Length b = new QuantityMeasurementApp.Length(1.0, "feet");
        assertTrue(a.equals(b), "1.0 feet should equal 1.0 feet");
    }

    public static void testEquality_InchToInch_SameValue() {
        QuantityMeasurementApp.Length a = new QuantityMeasurementApp.Length(1.0, "inch");
        QuantityMeasurementApp.Length b = new QuantityMeasurementApp.Length(1.0, "inch");
        assertTrue(a.equals(b), "1.0 inch should equal 1.0 inch");
    }

    public static void testEquality_FeetToInch_EquivalentValue() {
        QuantityMeasurementApp.Length feet = new QuantityMeasurementApp.Length(1.0, "feet");
        QuantityMeasurementApp.Length inch = new QuantityMeasurementApp.Length(12.0, "inch");
        assertTrue(feet.equals(inch), "1.0 feet should equal 12.0 inch");
    }

    public static void testEquality_InchToFeet_EquivalentValue() {
        QuantityMeasurementApp.Length inch = new QuantityMeasurementApp.Length(12.0, "inch");
        QuantityMeasurementApp.Length feet = new QuantityMeasurementApp.Length(1.0, "feet");
        assertTrue(inch.equals(feet), "12.0 inch should equal 1.0 feet");
    }

    public static void testEquality_FeetToFeet_DifferentValue() {
        QuantityMeasurementApp.Length oneFoot = new QuantityMeasurementApp.Length(1.0, "feet");
        QuantityMeasurementApp.Length twoFeet = new QuantityMeasurementApp.Length(2.0, "feet");
        assertFalse(oneFoot.equals(twoFeet), "1.0 feet should not equal 2.0 feet");
    }

    public static void testEquality_InchToInch_DifferentValue() {
        QuantityMeasurementApp.Length oneInch = new QuantityMeasurementApp.Length(1.0, "inch");
        QuantityMeasurementApp.Length twoInches = new QuantityMeasurementApp.Length(2.0, "inch");
        assertFalse(oneInch.equals(twoInches), "1.0 inch should not equal 2.0 inch");
    }

    public static void testEquality_SameReference() {
        QuantityMeasurementApp.Length value = new QuantityMeasurementApp.Length(1.0, "feet");
        assertTrue(value.equals(value), "Object should be equal to itself");
    }

    public static void testEquality_NullComparison() {
        QuantityMeasurementApp.Length value = new QuantityMeasurementApp.Length(1.0, "feet");
        assertFalse(value.equals(null), "Object should not equal null");
    }

    public static void testEquality_NonNumericInput() {
        QuantityMeasurementApp.Length value = new QuantityMeasurementApp.Length(1.0, "feet");
        Object nonNumericInput = "1.0";
        assertFalse(value.equals(nonNumericInput), "Length should not equal String input");
    }

    public static void testEquality_Symmetric() {
        QuantityMeasurementApp.Length a = new QuantityMeasurementApp.Length(1.0, "feet");
        QuantityMeasurementApp.Length b = new QuantityMeasurementApp.Length(12.0, "inch");
        assertTrue(a.equals(b), "Symmetric check part 1 failed");
        assertTrue(b.equals(a), "Symmetric check part 2 failed");
    }

    public static void testEquality_Transitive() {
        QuantityMeasurementApp.Length a = new QuantityMeasurementApp.Length(1.0, "feet");
        QuantityMeasurementApp.Length b = new QuantityMeasurementApp.Length(12.0, "inch");
        QuantityMeasurementApp.Length c = new QuantityMeasurementApp.Length(1.0, "feet");
        assertTrue(a.equals(b), "Transitive check a==b failed");
        assertTrue(b.equals(c), "Transitive check b==c failed");
        assertTrue(a.equals(c), "Transitive check a==c failed");
    }

    public static void testEquality_Consistent() {
        QuantityMeasurementApp.Length a = new QuantityMeasurementApp.Length(1.0, "feet");
        QuantityMeasurementApp.Length b = new QuantityMeasurementApp.Length(12.0, "inch");
        assertTrue(a.equals(b), "Consistent check first call failed");
        assertTrue(a.equals(b), "Consistent check second call failed");
        assertTrue(a.equals(b), "Consistent check third call failed");
    }

    public static void testEquality_InvalidUnit() {
        assertThrows(IllegalArgumentException.class, () -> new QuantityMeasurementApp.Length(1.0, "meter"),
                "Unsupported units should be rejected");
    }

    public static void testEquality_NullUnit() {
        assertThrows(IllegalArgumentException.class, () -> new QuantityMeasurementApp.Length(1.0, (String) null),
                "Null unit text should be rejected");
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityMeasurementApp.Length(1.0, (QuantityMeasurementApp.LengthUnit) null),
                "Null unit enum should be rejected");
    }

    public static void testTypeAndUnitSafety_EnumAcceptedUnits() {
        QuantityMeasurementApp.LengthUnit feet = QuantityMeasurementApp.LengthUnit.from("foot");
        QuantityMeasurementApp.LengthUnit inches = QuantityMeasurementApp.LengthUnit.from("inches");
        assertTrue(feet == QuantityMeasurementApp.LengthUnit.FEET, "foot should map to FEET enum");
        assertTrue(inches == QuantityMeasurementApp.LengthUnit.INCHES, "inches should map to INCHES enum");
    }

    public static void testUnitAbstraction_CodeConsolidation() {
        QuantityMeasurementApp.Length feet = new QuantityMeasurementApp.Length(2.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.Length inches = new QuantityMeasurementApp.Length(24.0, QuantityMeasurementApp.LengthUnit.INCHES);
        assertTrue(feet.equals(inches), "Single Length abstraction should behave consistently across units");
    }

    public static void testBackwardCompatibility_UC1_UC2_Scenarios() {
        testEquality_FeetToFeet_SameValue();
        testEquality_FeetToFeet_DifferentValue();
        testEquality_NullComparison();
        testEquality_NonNumericInput();
        testEquality_SameReference();
        testEquality_InchToInch_SameValue();
        testEquality_InchToInch_DifferentValue();
    }

    private static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }

    private static void assertFalse(boolean condition, String message) {
        assertTrue(!condition, message);
    }

    private static void assertThrows(Class<? extends Throwable> expected, Runnable action, String message) {
        try {
            action.run();
            throw new AssertionError(message + " (no exception thrown)");
        } catch (Throwable thrown) {
            if (!expected.isInstance(thrown)) {
                throw new AssertionError(message + " (expected " + expected.getSimpleName()
                        + ", got " + thrown.getClass().getSimpleName() + ")");
            }
        }
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
        runTest("testEquality_FeetToFeet_SameValue", QuantityMeasurementAppTest::testEquality_FeetToFeet_SameValue);
        runTest("testEquality_InchToInch_SameValue", QuantityMeasurementAppTest::testEquality_InchToInch_SameValue);
        runTest("testEquality_FeetToInch_EquivalentValue", QuantityMeasurementAppTest::testEquality_FeetToInch_EquivalentValue);
        runTest("testEquality_InchToFeet_EquivalentValue", QuantityMeasurementAppTest::testEquality_InchToFeet_EquivalentValue);
        runTest("testEquality_FeetToFeet_DifferentValue", QuantityMeasurementAppTest::testEquality_FeetToFeet_DifferentValue);
        runTest("testEquality_InchToInch_DifferentValue", QuantityMeasurementAppTest::testEquality_InchToInch_DifferentValue);
        runTest("testEquality_SameReference", QuantityMeasurementAppTest::testEquality_SameReference);
        runTest("testEquality_NullComparison", QuantityMeasurementAppTest::testEquality_NullComparison);
        runTest("testEquality_NonNumericInput", QuantityMeasurementAppTest::testEquality_NonNumericInput);
        runTest("testEquality_Symmetric", QuantityMeasurementAppTest::testEquality_Symmetric);
        runTest("testEquality_Transitive", QuantityMeasurementAppTest::testEquality_Transitive);
        runTest("testEquality_Consistent", QuantityMeasurementAppTest::testEquality_Consistent);
        runTest("testEquality_InvalidUnit", QuantityMeasurementAppTest::testEquality_InvalidUnit);
        runTest("testEquality_NullUnit", QuantityMeasurementAppTest::testEquality_NullUnit);
        runTest("testTypeAndUnitSafety_EnumAcceptedUnits", QuantityMeasurementAppTest::testTypeAndUnitSafety_EnumAcceptedUnits);
        runTest("testUnitAbstraction_CodeConsolidation", QuantityMeasurementAppTest::testUnitAbstraction_CodeConsolidation);
        runTest("testBackwardCompatibility_UC1_UC2_Scenarios", QuantityMeasurementAppTest::testBackwardCompatibility_UC1_UC2_Scenarios);

        System.out.println();
        System.out.println("Total: " + (passed + failed));
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);

        if (failed > 0) {
            System.exit(1);
        }
    }
}
