import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {
    private static final double EPSILON = 1e-5;

    private void assertLength(double expectedValue, QuantityMeasurementApp.LengthUnit expectedUnit,
            QuantityMeasurementApp.Length actual) {
        assertEquals(expectedUnit, actual.getUnit());
        assertEquals(expectedValue, actual.getValue(), EPSILON);
    }

    @Test
    public void testFeetEquality_SameValue() {
        QuantityMeasurementApp.Length f1 = new QuantityMeasurementApp.Length(1, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.Length f2 = new QuantityMeasurementApp.Length(1, QuantityMeasurementApp.LengthUnit.FEET);
        assertEquals(f1, f2);
    }

    @Test
    public void testFeetEquality_DifferentValue() {
        QuantityMeasurementApp.Length f1 = new QuantityMeasurementApp.Length(1, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.Length f2 = new QuantityMeasurementApp.Length(2, QuantityMeasurementApp.LengthUnit.FEET);
        assertNotEquals(f1, f2);
    }

    @Test
    public void testInchesEquality_SameValue() {
        QuantityMeasurementApp.Length i1 = new QuantityMeasurementApp.Length(1, QuantityMeasurementApp.LengthUnit.INCHES);
        QuantityMeasurementApp.Length i2 = new QuantityMeasurementApp.Length(1, QuantityMeasurementApp.LengthUnit.INCHES);
        assertEquals(i1, i2);
    }

    @Test
    public void testInchesEquality_DifferentValue() {
        QuantityMeasurementApp.Length i1 = new QuantityMeasurementApp.Length(1, QuantityMeasurementApp.LengthUnit.INCHES);
        QuantityMeasurementApp.Length i2 = new QuantityMeasurementApp.Length(2, QuantityMeasurementApp.LengthUnit.INCHES);
        assertNotEquals(i1, i2);
    }

    @Test
    public void testYardsEquality_SameValue() {
        QuantityMeasurementApp.Length y1 = new QuantityMeasurementApp.Length(10, QuantityMeasurementApp.LengthUnit.YARDS);
        QuantityMeasurementApp.Length y2 = new QuantityMeasurementApp.Length(10, QuantityMeasurementApp.LengthUnit.YARDS);
        assertEquals(y1, y2);
    }

    @Test
    public void testYardsEquality_DifferentValue() {
        QuantityMeasurementApp.Length y1 = new QuantityMeasurementApp.Length(1, QuantityMeasurementApp.LengthUnit.YARDS);
        QuantityMeasurementApp.Length y2 = new QuantityMeasurementApp.Length(2, QuantityMeasurementApp.LengthUnit.YARDS);
        assertNotEquals(y1, y2);
    }

    @Test
    public void testCentimetersEquality_SameValue() {
        QuantityMeasurementApp.Length c1 = new QuantityMeasurementApp.Length(1, QuantityMeasurementApp.LengthUnit.CENTIMETERS);
        QuantityMeasurementApp.Length c2 = new QuantityMeasurementApp.Length(1, QuantityMeasurementApp.LengthUnit.CENTIMETERS);
        assertEquals(c1, c2);
    }

    @Test
    public void testCentimetersEquality_DifferentValue() {
        QuantityMeasurementApp.Length c1 = new QuantityMeasurementApp.Length(1, QuantityMeasurementApp.LengthUnit.CENTIMETERS);
        QuantityMeasurementApp.Length c2 = new QuantityMeasurementApp.Length(2, QuantityMeasurementApp.LengthUnit.CENTIMETERS);
        assertNotEquals(c1, c2);
    }

    @Test
    public void testEquality_SameReference() {
        QuantityMeasurementApp.Length f1 = new QuantityMeasurementApp.Length(1, QuantityMeasurementApp.LengthUnit.FEET);
        assertEquals(f1, f1);
    }

    @Test
    public void testCrossUnitEquality() {
        QuantityMeasurementApp.Length f1 = new QuantityMeasurementApp.Length(1, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.Length i1 = new QuantityMeasurementApp.Length(12, QuantityMeasurementApp.LengthUnit.INCHES);
        assertEquals(f1, i1);
    }

    @Test
    public void testEquality_AllUnits_ComplexScenario() {
        QuantityMeasurementApp.Length[] length = {
                new QuantityMeasurementApp.Length(9, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.Length(108, QuantityMeasurementApp.LengthUnit.INCHES),
                new QuantityMeasurementApp.Length(3, QuantityMeasurementApp.LengthUnit.YARDS)
        };

        for (int i = 0; i < length.length; i++) {
            for (int j = 0; j < length.length; j++) {
                assertEquals(length[i], length[j]);
            }
        }
    }

    @Test
    public void testConversion_ZeroValue() {
        QuantityMeasurementApp.Length actualVal = new QuantityMeasurementApp.Length(0, QuantityMeasurementApp.LengthUnit.CENTIMETERS)
                .convertTo(QuantityMeasurementApp.LengthUnit.INCHES);
        QuantityMeasurementApp.Length expectedLen = new QuantityMeasurementApp.Length(0, QuantityMeasurementApp.LengthUnit.INCHES);
        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(actualVal, expectedLen));
    }

    @Test
    public void testEquality_InvalidUnit_Throws() {
        assertThrows(IllegalArgumentException.class, () -> {
            new QuantityMeasurementApp.Length(10, "meter");
        });
    }

    @Test
    public void testConversion_InvalidUnit_Throws() {
        QuantityMeasurementApp.Length length = new QuantityMeasurementApp.Length(10, QuantityMeasurementApp.LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> {
            QuantityMeasurementApp.demonstrateLengthConversion(length, null);
        });
    }

    @Test
    public void testAddition_SameUnit_FeetPlusFeet() {
        QuantityMeasurementApp.Length result = QuantityMeasurementApp.Length.add(
                new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.Length(2.0, QuantityMeasurementApp.LengthUnit.FEET),
                QuantityMeasurementApp.LengthUnit.FEET);

        assertLength(3.0, QuantityMeasurementApp.LengthUnit.FEET, result);
    }

    @Test
    public void testAddition_SameUnit_InchPlusInch() {
        QuantityMeasurementApp.Length result = QuantityMeasurementApp.Length.add(
                new QuantityMeasurementApp.Length(6.0, QuantityMeasurementApp.LengthUnit.INCHES),
                new QuantityMeasurementApp.Length(6.0, QuantityMeasurementApp.LengthUnit.INCHES),
                QuantityMeasurementApp.LengthUnit.INCHES);

        assertLength(12.0, QuantityMeasurementApp.LengthUnit.INCHES, result);
    }

    @Test
    public void testAddition_CrossUnit_FeetPlusInches() {
        QuantityMeasurementApp.Length result = QuantityMeasurementApp.Length.add(
                new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.Length(12.0, QuantityMeasurementApp.LengthUnit.INCHES),
                QuantityMeasurementApp.LengthUnit.FEET);

        assertLength(2.0, QuantityMeasurementApp.LengthUnit.FEET, result);
    }

    @Test
    public void testAddition_CrossUnit_InchPlusFeet() {
        QuantityMeasurementApp.Length result = QuantityMeasurementApp.Length.add(
                new QuantityMeasurementApp.Length(12.0, QuantityMeasurementApp.LengthUnit.INCHES),
                new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                QuantityMeasurementApp.LengthUnit.INCHES);

        assertLength(24.0, QuantityMeasurementApp.LengthUnit.INCHES, result);
    }

    @Test
    public void testAddition_CrossUnit_YardPlusFeet() {
        QuantityMeasurementApp.Length result = QuantityMeasurementApp.Length.add(
                new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.LengthUnit.YARDS),
                new QuantityMeasurementApp.Length(3.0, QuantityMeasurementApp.LengthUnit.FEET),
                QuantityMeasurementApp.LengthUnit.YARDS);

        assertLength(2.0, QuantityMeasurementApp.LengthUnit.YARDS, result);
    }

    @Test
    public void testAddition_CrossUnit_CentimeterPlusInch() {
        QuantityMeasurementApp.Length result = QuantityMeasurementApp.Length.add(
                new QuantityMeasurementApp.Length(2.54, QuantityMeasurementApp.LengthUnit.CENTIMETERS),
                new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.LengthUnit.INCHES),
                QuantityMeasurementApp.LengthUnit.CENTIMETERS);

        assertLength(5.08, QuantityMeasurementApp.LengthUnit.CENTIMETERS, result);
    }

    @Test
    public void testAddition_Commutativity() {
        QuantityMeasurementApp.Length a = new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.Length b = new QuantityMeasurementApp.Length(12.0, QuantityMeasurementApp.LengthUnit.INCHES);

        QuantityMeasurementApp.Length sumAB = QuantityMeasurementApp.Length.add(a, b, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.Length sumBA = QuantityMeasurementApp.Length.add(b, a, QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(sumAB, sumBA);
        assertLength(2.0, QuantityMeasurementApp.LengthUnit.FEET, sumAB);
    }

    @Test
    public void testAddition_WithZero() {
        QuantityMeasurementApp.Length result = QuantityMeasurementApp.Length.add(
                new QuantityMeasurementApp.Length(5.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.Length(0.0, QuantityMeasurementApp.LengthUnit.INCHES),
                QuantityMeasurementApp.LengthUnit.FEET);

        assertLength(5.0, QuantityMeasurementApp.LengthUnit.FEET, result);
    }

    @Test
    public void testAddition_NegativeValues() {
        QuantityMeasurementApp.Length result = QuantityMeasurementApp.Length.add(
                new QuantityMeasurementApp.Length(5.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.Length(-2.0, QuantityMeasurementApp.LengthUnit.FEET),
                QuantityMeasurementApp.LengthUnit.FEET);

        assertLength(3.0, QuantityMeasurementApp.LengthUnit.FEET, result);
    }

    @Test
    public void testAddition_NullSecondOperand() {
        QuantityMeasurementApp.Length first = new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> {
            QuantityMeasurementApp.Length.add(first, null, QuantityMeasurementApp.LengthUnit.FEET);
        });
    }

    @Test
    public void testAddition_InvalidTargetUnit_Throws() {
        QuantityMeasurementApp.Length first = new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.Length second = new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> {
            QuantityMeasurementApp.Length.add(first, second, null);
        });
    }

    @Test
    public void testAddition_LargeValues() {
        QuantityMeasurementApp.Length result = QuantityMeasurementApp.Length.add(
                new QuantityMeasurementApp.Length(1e6, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.Length(1e6, QuantityMeasurementApp.LengthUnit.FEET),
                QuantityMeasurementApp.LengthUnit.FEET);

        assertLength(2e6, QuantityMeasurementApp.LengthUnit.FEET, result);
    }

    @Test
    public void testAddition_SmallValues() {
        QuantityMeasurementApp.Length result = QuantityMeasurementApp.Length.add(
                new QuantityMeasurementApp.Length(0.001, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.Length(0.002, QuantityMeasurementApp.LengthUnit.FEET),
                QuantityMeasurementApp.LengthUnit.FEET);

        assertLength(0.003, QuantityMeasurementApp.LengthUnit.FEET, result);
    }
}