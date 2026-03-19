public class Length {
    private static final double EPSILON = 1e-9;

    private final double value;
    private final LengthUnit unit;

    public Length(double value, LengthUnit unit) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Length value must be a finite number");
        }
        if (unit == null) {
            throw new IllegalArgumentException("Length unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }

    public Length(double value, String unitText) {
        this(value, LengthUnit.from(unitText));
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    private double toFeet() {
        return unit.convertToBaseUnit(value);
    }

    public static double convert(double value, LengthUnit sourceUnit, LengthUnit targetUnit) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Length value must be a finite number");
        }
        if (sourceUnit == null) {
            throw new IllegalArgumentException("Source unit cannot be null");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double baseValue = sourceUnit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(baseValue);
        return converted;
    }

    public Length convertTo(LengthUnit targetUnit) {
        double convertedValue = convert(this.value, this.unit, targetUnit);
        return new Length(convertedValue, targetUnit);
    }

    public Length convertTo(LengthUnit targetUnit, int decimalPlaces) {
        if (decimalPlaces < 0) {
            throw new IllegalArgumentException("Decimal places must be non-negative");
        }
        Length converted = convertTo(targetUnit);
        double scale = Math.pow(10, decimalPlaces);
        double rounded = Math.round(converted.value * scale) / scale;
        return new Length(rounded, targetUnit);
    }

    public Length add(Length other) {
        return add(this, other, this.unit);
    }

    public Length add(Length other, LengthUnit targetUnit) {
        return addAndConvert(this, other, targetUnit);
    }

    public static Length add(Length length1, Length length2) {
        if (length1 == null) {
            throw new IllegalArgumentException("First length cannot be null");
        }
        return add(length1, length2, length1.unit);
    }

    public static Length add(Length length1, Length length2, LengthUnit targetUnit) {
        return addAndConvert(length1, length2, targetUnit);
    }

    private static Length addAndConvert(Length length1, Length length2, LengthUnit targetUnit) {
        if (length1 == null) {
            throw new IllegalArgumentException("First length cannot be null");
        }
        if (length2 == null) {
            throw new IllegalArgumentException("Second length cannot be null");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double sumInFeet = length1.toFeet() + length2.toFeet();
        if (!Double.isFinite(sumInFeet)) {
            throw new IllegalArgumentException("Sum is out of range");
        }

        double sumInTarget = targetUnit.convertFromBaseUnit(sumInFeet);
        return new Length(sumInTarget, targetUnit);
    }

    public static Length add(double value1, LengthUnit unit1, double value2, LengthUnit unit2, LengthUnit targetUnit) {
        return add(new Length(value1, unit1), new Length(value2, unit2), targetUnit);
    }

    public static Length add(double value1, LengthUnit unit1, double value2, LengthUnit unit2) {
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
        Length other = (Length) obj;
        return Math.abs(this.toFeet() - other.toFeet()) < EPSILON;
    }

    @Override
    public int hashCode() {
        double normalizedFeet = Math.round(toFeet() / EPSILON) * EPSILON;
        return Double.hashCode(normalizedFeet);
    }

    @Override
    public String toString() {
        return String.format("Length[value=%.6f, unit=%s]", value, unit);
    }
}