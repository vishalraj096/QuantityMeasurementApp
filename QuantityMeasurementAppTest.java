import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {
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
}
