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
		assertThrows(IllegalArgumentException.class, () -> new QuantityMeasurementApp.Length(10, "meter"));
	}

	@Test
	public void testConversion_InvalidUnit_Throws() {
		QuantityMeasurementApp.Length length = new QuantityMeasurementApp.Length(10, QuantityMeasurementApp.LengthUnit.FEET);
		assertThrows(IllegalArgumentException.class, () -> QuantityMeasurementApp.demonstrateLengthConversion(length, null));
	}

	@Test
	public void thirtyPoint48CmEqualsOneFoot() {
		QuantityMeasurementApp.Length foot = new QuantityMeasurementApp.Length(1, QuantityMeasurementApp.LengthUnit.FEET);
		double oneFootInCm = QuantityMeasurementApp.Length.convert(1.0,
			QuantityMeasurementApp.LengthUnit.FEET,
			QuantityMeasurementApp.LengthUnit.CENTIMETERS);
		QuantityMeasurementApp.Length cm = new QuantityMeasurementApp.Length(oneFootInCm,
			QuantityMeasurementApp.LengthUnit.CENTIMETERS);
		assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(foot, cm));
	}

	@Test
	public void addFeetAndInches() {
		QuantityMeasurementApp.Length length1 = new QuantityMeasurementApp.Length(1, QuantityMeasurementApp.LengthUnit.FEET);
		QuantityMeasurementApp.Length length2 = new QuantityMeasurementApp.Length(12, QuantityMeasurementApp.LengthUnit.INCHES);
		QuantityMeasurementApp.Length sumLength = QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, QuantityMeasurementApp.LengthUnit.FEET);
		QuantityMeasurementApp.Length expectedLength = new QuantityMeasurementApp.Length(2, QuantityMeasurementApp.LengthUnit.FEET);
		assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(sumLength, expectedLength));
	}

	@Test
	public void addYardsAndCentimeters() {
		QuantityMeasurementApp.Length length1 = new QuantityMeasurementApp.Length(1, QuantityMeasurementApp.LengthUnit.YARDS);
		QuantityMeasurementApp.Length length2 = new QuantityMeasurementApp.Length(90, QuantityMeasurementApp.LengthUnit.CENTIMETERS);
		QuantityMeasurementApp.Length sumLength = QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, QuantityMeasurementApp.LengthUnit.YARDS);
		assertLength(1.984252, QuantityMeasurementApp.LengthUnit.YARDS, sumLength);
	}

	@Test
	public void addFeetAndYardsWithTargetUnitCentimeters() {
		QuantityMeasurementApp.Length length1 = new QuantityMeasurementApp.Length(2, QuantityMeasurementApp.LengthUnit.YARDS);
		QuantityMeasurementApp.Length length2 = new QuantityMeasurementApp.Length(4, QuantityMeasurementApp.LengthUnit.FEET);
		QuantityMeasurementApp.Length sumLength = QuantityMeasurementApp.demonstrateLengthAddition(length1, length2,
				QuantityMeasurementApp.LengthUnit.CENTIMETERS);
		double expectedCm = QuantityMeasurementApp.Length.convert(10.0,
			QuantityMeasurementApp.LengthUnit.FEET,
			QuantityMeasurementApp.LengthUnit.CENTIMETERS);
		assertLength(expectedCm, QuantityMeasurementApp.LengthUnit.CENTIMETERS, sumLength);
	}

	@Test
	public void testAddition_ExplicitTargetUnit_Feet() {
		QuantityMeasurementApp.Length result = QuantityMeasurementApp.Length.add(
				new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.LengthUnit.FEET),
				new QuantityMeasurementApp.Length(12.0, QuantityMeasurementApp.LengthUnit.INCHES),
				QuantityMeasurementApp.LengthUnit.FEET);
		assertLength(2.0, QuantityMeasurementApp.LengthUnit.FEET, result);
	}

	@Test
	public void testAddition_ExplicitTargetUnit_Inches() {
		QuantityMeasurementApp.Length result = QuantityMeasurementApp.Length.add(
				new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.LengthUnit.FEET),
				new QuantityMeasurementApp.Length(12.0, QuantityMeasurementApp.LengthUnit.INCHES),
				QuantityMeasurementApp.LengthUnit.INCHES);
		assertLength(24.0, QuantityMeasurementApp.LengthUnit.INCHES, result);
	}

	@Test
	public void testAddition_ExplicitTargetUnit_Yards() {
		QuantityMeasurementApp.Length result = QuantityMeasurementApp.Length.add(
				new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.LengthUnit.FEET),
				new QuantityMeasurementApp.Length(12.0, QuantityMeasurementApp.LengthUnit.INCHES),
				QuantityMeasurementApp.LengthUnit.YARDS);
		assertLength(0.666667, QuantityMeasurementApp.LengthUnit.YARDS, result);
	}

	@Test
	public void testAddition_ExplicitTargetUnit_Centimeters() {
		QuantityMeasurementApp.Length result = QuantityMeasurementApp.Length.add(
				new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.LengthUnit.INCHES),
				new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.LengthUnit.INCHES),
				QuantityMeasurementApp.LengthUnit.CENTIMETERS);
		assertLength(5.08, QuantityMeasurementApp.LengthUnit.CENTIMETERS, result);
	}

	@Test
	public void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {
		QuantityMeasurementApp.Length result = QuantityMeasurementApp.Length.add(
				new QuantityMeasurementApp.Length(2.0, QuantityMeasurementApp.LengthUnit.YARDS),
				new QuantityMeasurementApp.Length(3.0, QuantityMeasurementApp.LengthUnit.FEET),
				QuantityMeasurementApp.LengthUnit.YARDS);
		assertLength(3.0, QuantityMeasurementApp.LengthUnit.YARDS, result);
	}

	@Test
	public void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {
		QuantityMeasurementApp.Length result = QuantityMeasurementApp.Length.add(
				new QuantityMeasurementApp.Length(2.0, QuantityMeasurementApp.LengthUnit.YARDS),
				new QuantityMeasurementApp.Length(3.0, QuantityMeasurementApp.LengthUnit.FEET),
				QuantityMeasurementApp.LengthUnit.FEET);
		assertLength(9.0, QuantityMeasurementApp.LengthUnit.FEET, result);
	}

	@Test
	public void testAddition_ExplicitTargetUnit_Commutativity() {
		QuantityMeasurementApp.Length ab = QuantityMeasurementApp.Length.add(
				new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.LengthUnit.FEET),
				new QuantityMeasurementApp.Length(12.0, QuantityMeasurementApp.LengthUnit.INCHES),
				QuantityMeasurementApp.LengthUnit.YARDS);
		QuantityMeasurementApp.Length ba = QuantityMeasurementApp.Length.add(
				new QuantityMeasurementApp.Length(12.0, QuantityMeasurementApp.LengthUnit.INCHES),
				new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.LengthUnit.FEET),
				QuantityMeasurementApp.LengthUnit.YARDS);

		assertEquals(ab, ba);
		assertEquals(QuantityMeasurementApp.LengthUnit.YARDS, ab.getUnit());
	}

	@Test
	public void testAddition_ExplicitTargetUnit_WithZero() {
		QuantityMeasurementApp.Length result = QuantityMeasurementApp.Length.add(
				new QuantityMeasurementApp.Length(5.0, QuantityMeasurementApp.LengthUnit.FEET),
				new QuantityMeasurementApp.Length(0.0, QuantityMeasurementApp.LengthUnit.INCHES),
				QuantityMeasurementApp.LengthUnit.YARDS);
		assertLength(1.666667, QuantityMeasurementApp.LengthUnit.YARDS, result);
	}

	@Test
	public void testAddition_ExplicitTargetUnit_NegativeValues() {
		QuantityMeasurementApp.Length result = QuantityMeasurementApp.Length.add(
				new QuantityMeasurementApp.Length(5.0, QuantityMeasurementApp.LengthUnit.FEET),
				new QuantityMeasurementApp.Length(-2.0, QuantityMeasurementApp.LengthUnit.FEET),
				QuantityMeasurementApp.LengthUnit.INCHES);
		assertLength(36.0, QuantityMeasurementApp.LengthUnit.INCHES, result);
	}

	@Test
	public void testAddition_ExplicitTargetUnit_NullTargetUnit() {
		assertThrows(IllegalArgumentException.class, () -> QuantityMeasurementApp.Length.add(
				new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.LengthUnit.FEET),
				new QuantityMeasurementApp.Length(12.0, QuantityMeasurementApp.LengthUnit.INCHES),
				null));
	}

	@Test
	public void testAddition_ExplicitTargetUnit_LargeToSmallScale() {
		QuantityMeasurementApp.Length result = QuantityMeasurementApp.Length.add(
				new QuantityMeasurementApp.Length(1000.0, QuantityMeasurementApp.LengthUnit.FEET),
				new QuantityMeasurementApp.Length(500.0, QuantityMeasurementApp.LengthUnit.FEET),
				QuantityMeasurementApp.LengthUnit.INCHES);
		assertLength(18000.0, QuantityMeasurementApp.LengthUnit.INCHES, result);
	}

	@Test
	public void testAddition_ExplicitTargetUnit_SmallToLargeScale() {
		QuantityMeasurementApp.Length result = QuantityMeasurementApp.Length.add(
				new QuantityMeasurementApp.Length(12.0, QuantityMeasurementApp.LengthUnit.INCHES),
				new QuantityMeasurementApp.Length(12.0, QuantityMeasurementApp.LengthUnit.INCHES),
				QuantityMeasurementApp.LengthUnit.YARDS);
		assertLength(0.666667, QuantityMeasurementApp.LengthUnit.YARDS, result);
	}

	@Test
	public void testAddition_ExplicitTargetUnit_AllUnitCombinations() {
		QuantityMeasurementApp.LengthUnit[] units = QuantityMeasurementApp.LengthUnit.values();
		for (QuantityMeasurementApp.LengthUnit u1 : units) {
			for (QuantityMeasurementApp.LengthUnit u2 : units) {
				for (QuantityMeasurementApp.LengthUnit target : units) {
					QuantityMeasurementApp.Length a = new QuantityMeasurementApp.Length(2.5, u1);
					QuantityMeasurementApp.Length b = new QuantityMeasurementApp.Length(1.25, u2);
					QuantityMeasurementApp.Length ab = QuantityMeasurementApp.Length.add(a, b, target);
					QuantityMeasurementApp.Length ba = QuantityMeasurementApp.Length.add(b, a, target);
					assertEquals(target, ab.getUnit());
					assertEquals(ab, ba);
				}
			}
		}
	}

	@Test
	public void testAddition_ExplicitTargetUnit_PrecisionTolerance() {
		QuantityMeasurementApp.Length result1 = QuantityMeasurementApp.Length.add(
				new QuantityMeasurementApp.Length(2.54, QuantityMeasurementApp.LengthUnit.CENTIMETERS),
				new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.LengthUnit.INCHES),
				QuantityMeasurementApp.LengthUnit.CENTIMETERS);
		QuantityMeasurementApp.Length result2 = QuantityMeasurementApp.Length.add(
				new QuantityMeasurementApp.Length(1.0, QuantityMeasurementApp.LengthUnit.FEET),
				new QuantityMeasurementApp.Length(12.0, QuantityMeasurementApp.LengthUnit.INCHES),
				QuantityMeasurementApp.LengthUnit.YARDS);

		assertLength(5.08, QuantityMeasurementApp.LengthUnit.CENTIMETERS, result1);
		assertLength(0.666667, QuantityMeasurementApp.LengthUnit.YARDS, result2);
	}
}
