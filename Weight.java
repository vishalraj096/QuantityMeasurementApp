public class Weight {
    private static final double EPSILON = 1e-6;

    private final double value;
    private final WeightUnit unit;

    public Weight(double value, WeightUnit unit) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Weight value must be a finite number");
        }
        if (unit == null) {
            throw new IllegalArgumentException("Weight unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }

    public Weight(double value, String unitText) {
        this(value, WeightUnit.from(unitText));
    }

    public double getValue() {
        return value;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    private double toKilogram() {
        return unit.convertToBaseUnit(value);
    }

    public static double convert(double value, WeightUnit sourceUnit, WeightUnit targetUnit) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Weight value must be a finite number");
        }
        if (sourceUnit == null) {
            throw new IllegalArgumentException("Source unit cannot be null");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double baseValue = sourceUnit.convertToBaseUnit(value);
        return targetUnit.convertFromBaseUnit(baseValue);
    }

    public Weight convertTo(WeightUnit targetUnit) {
        double convertedValue = convert(this.value, this.unit, targetUnit);
        return new Weight(convertedValue, targetUnit);
    }

    public Weight add(Weight other) {
        return add(this, other, this.unit);
    }

    public Weight add(Weight other, WeightUnit targetUnit) {
        return addAndConvert(this, other, targetUnit);
    }

    public static Weight add(Weight weight1, Weight weight2) {
        if (weight1 == null) {
            throw new IllegalArgumentException("First weight cannot be null");
        }
        return add(weight1, weight2, weight1.unit);
    }

    public static Weight add(Weight weight1, Weight weight2, WeightUnit targetUnit) {
        return addAndConvert(weight1, weight2, targetUnit);
    }

    private static Weight addAndConvert(Weight weight1, Weight weight2, WeightUnit targetUnit) {
        if (weight1 == null) {
            throw new IllegalArgumentException("First weight cannot be null");
        }
        if (weight2 == null) {
            throw new IllegalArgumentException("Second weight cannot be null");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double sumInKg = weight1.toKilogram() + weight2.toKilogram();
        if (!Double.isFinite(sumInKg)) {
            throw new IllegalArgumentException("Sum is out of range");
        }

        double sumInTarget = targetUnit.convertFromBaseUnit(sumInKg);
        return new Weight(sumInTarget, targetUnit);
    }

    public static Weight add(double value1, WeightUnit unit1, double value2, WeightUnit unit2, WeightUnit targetUnit) {
        return add(new Weight(value1, unit1), new Weight(value2, unit2), targetUnit);
    }

    public static Weight add(double value1, WeightUnit unit1, double value2, WeightUnit unit2) {
        return add(value1, unit1, value2, unit2, unit1);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Weight other = (Weight) obj;
        return Math.abs(this.toKilogram() - other.toKilogram()) < EPSILON;
    }

    @Override
    public int hashCode() {
        double normalizedKg = Math.round(toKilogram() / EPSILON) * EPSILON;
        return Double.hashCode(normalizedKg);
    }

    @Override
    public String toString() {
        return String.format("Weight[value=%.6f, unit=%s]", value, unit);
    }
}
