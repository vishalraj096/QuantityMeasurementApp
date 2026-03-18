public class QuantityMeasurementApp {
    public enum LengthUnit {
        FEET(1.0),
        INCHES(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETERS(0.393701 / 12.0);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double getToFeetFactor() {
            return toFeetFactor;
        }

        public static LengthUnit from(String unitText) {
            if (unitText == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            String normalized = unitText.trim().toUpperCase();
            if (normalized.equals("FOOT") || normalized.equals("FEET")) {
                return FEET;
            }
            if (normalized.equals("INCH") || normalized.equals("INCHES")) {
                return INCHES;
            }
            if (normalized.equals("YARD") || normalized.equals("YARDS") || normalized.equals("YD")) {
                return YARDS;
            }
            if (normalized.equals("CENTIMETER") || normalized.equals("CENTIMETERS") || normalized.equals("CM")) {
                return CENTIMETERS;
            }
            throw new IllegalArgumentException("Unsupported unit: " + unitText);
        }
    }

    public static class Length {
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
            return value * unit.getToFeetFactor();
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

            double converted = value * sourceUnit.getToFeetFactor() / targetUnit.getToFeetFactor();
            if (!Double.isFinite(converted)) {
                throw new IllegalArgumentException("Converted value is out of range");
            }
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

            double sumInTarget = convert(sumInFeet, LengthUnit.FEET, targetUnit);
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

    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        return length1.equals(length2);
    }

    public static boolean demonstrateLengthComparison(double value1, LengthUnit unit1, double value2, LengthUnit unit2) {
        Length length1 = new Length(value1, unit1);
        Length length2 = new Length(value2, unit2);
        return demonstrateLengthEquality(length1, length2);
    }

    public static Length demonstrateLengthConversion(double value, LengthUnit fromUnit, LengthUnit toUnit) {
        Length source = new Length(value, fromUnit);
        Length converted = source.convertTo(toUnit);
        System.out.println("convert(" + value + ", " + fromUnit + ", " + toUnit + ") = " + converted.getValue());
        return converted;
    }

    public static Length demonstrateLengthConversion(Length length, LengthUnit toUnit) {
        Length converted = length.convertTo(toUnit);
        System.out.println("convert(" + length.getValue() + ", " + length.getUnit() + ", " + toUnit + ") = " + converted.getValue());
        return converted;
    }

    public static Length demonstrateLengthAddition(Length length1, Length length2) {
        Length result = length1.add(length2);
        System.out.println("add(" + length1 + ", " + length2 + ") = " + result);
        return result;
    }

    public static Length demonstrateLengthAddition(Length length1, Length length2, LengthUnit targetUnit) {
        Length result = Length.add(length1, length2, targetUnit);
        System.out.println("add(" + length1 + ", " + length2 + ", " + targetUnit + ") = " + result);
        return result;
    }

    public static Length demonstrateLengthAddition(double value1, LengthUnit unit1, double value2, LengthUnit unit2, LengthUnit targetUnit) {
        Length result = Length.add(value1, unit1, value2, unit2, targetUnit);
        System.out.println("add(Quantity(" + value1 + ", " + unit1 + "), Quantity(" + value2 + ", " + unit2 + "), " + targetUnit + ") = " + result);
        return result;
    }

    public static void demonstrateFeetEquality() {
        Length obj1 = new Length(6.4, LengthUnit.FEET);
        Length obj2 = new Length(6.4, LengthUnit.FEET);

        if (obj1.equals(obj2)) {
            System.out.println("Both are equal in feet");
        } else {
            System.out.println("Values are different");
        }
    }

    public static void demonstrateInchesEquality() {
        Length obj1 = new Length(12.0, LengthUnit.INCHES);
        Length obj2 = new Length(12.0, "inch");

        if (obj1.equals(obj2)) {
            System.out.println("Both are equal in inches");
        } else {
            System.out.println("Values are different");
        }
    }

    public static void demonstrateFeetInchesComparison() {
        Length obj1 = new Length(1.0, "feet");
        Length obj2 = new Length(12.0, "inches");

        System.out.println("Are 1 foot and 12 inches equal? " + obj1.equals(obj2));
    }

    public static void demonstrateExtendedUnitSupport() {
        Length yardVsFeet1 = new Length(1.0, LengthUnit.YARDS);
        Length yardVsFeet2 = new Length(3.0, LengthUnit.FEET);
        System.out.println("Are 1 yard and 3 feet equal? " + yardVsFeet1.equals(yardVsFeet2));

        Length yardVsInch1 = new Length(1.0, LengthUnit.YARDS);
        Length yardVsInch2 = new Length(36.0, LengthUnit.INCHES);
        System.out.println("Are 1 yard and 36 inches equal? " + yardVsInch1.equals(yardVsInch2));

        Length yardVsYard1 = new Length(2.0, LengthUnit.YARDS);
        Length yardVsYard2 = new Length(2.0, "yard");
        System.out.println("Are 2 yards and 2 yards equal? " + yardVsYard1.equals(yardVsYard2));

        Length cmVsCm1 = new Length(2.0, LengthUnit.CENTIMETERS);
        Length cmVsCm2 = new Length(2.0, "cm");
        System.out.println("Are 2 cm and 2 cm equal? " + cmVsCm1.equals(cmVsCm2));

        Length cmVsInch1 = new Length(1.0, LengthUnit.CENTIMETERS);
        Length cmVsInch2 = new Length(0.393701, LengthUnit.INCHES);
        System.out.println("Are 1 cm and 0.393701 inches equal? " + cmVsInch1.equals(cmVsInch2));
    }

    public static void demonstrateUnitToUnitConversion() {
        demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCHES);
        demonstrateLengthConversion(3.0, LengthUnit.YARDS, LengthUnit.FEET);
        demonstrateLengthConversion(36.0, LengthUnit.INCHES, LengthUnit.YARDS);
        demonstrateLengthConversion(1.0, LengthUnit.CENTIMETERS, LengthUnit.INCHES);
        demonstrateLengthConversion(0.0, LengthUnit.FEET, LengthUnit.INCHES);
        demonstrateLengthConversion(-1.0, LengthUnit.FEET, LengthUnit.INCHES);

        Length lengthInYards = new Length(2.0, LengthUnit.YARDS);
        demonstrateLengthConversion(lengthInYards, LengthUnit.INCHES);

        double roundTrip = Length.convert(Length.convert(Length.convert(5.5, LengthUnit.FEET, LengthUnit.CENTIMETERS), LengthUnit.CENTIMETERS, LengthUnit.YARDS), LengthUnit.YARDS, LengthUnit.FEET);
        System.out.println("Round-trip 5.5 feet -> cm -> yards -> feet = " + roundTrip);

        System.out.println("Same-unit convert(5.0, FEET, FEET) = " + Length.convert(5.0, LengthUnit.FEET, LengthUnit.FEET));
    }

    public static void demonstrateLengthAdditionOperations() {
        demonstrateLengthAddition(new Length(1.0, LengthUnit.FEET), new Length(2.0, LengthUnit.FEET));
        demonstrateLengthAddition(new Length(1.0, LengthUnit.FEET), new Length(12.0, LengthUnit.INCHES));
        demonstrateLengthAddition(new Length(12.0, LengthUnit.INCHES), new Length(1.0, LengthUnit.FEET));
        demonstrateLengthAddition(new Length(1.0, LengthUnit.YARDS), new Length(3.0, LengthUnit.FEET));
        demonstrateLengthAddition(new Length(36.0, LengthUnit.INCHES), new Length(1.0, LengthUnit.YARDS));
        demonstrateLengthAddition(new Length(2.54, LengthUnit.CENTIMETERS), new Length(1.0, LengthUnit.INCHES));
        demonstrateLengthAddition(new Length(5.0, LengthUnit.FEET), new Length(0.0, LengthUnit.INCHES));
        demonstrateLengthAddition(new Length(5.0, LengthUnit.FEET), new Length(-2.0, LengthUnit.FEET));

        demonstrateLengthAddition(1.0, LengthUnit.FEET, 12.0, LengthUnit.INCHES, LengthUnit.INCHES);

        Length a = new Length(1.0, LengthUnit.FEET);
        Length b = new Length(12.0, LengthUnit.INCHES);
        Length ab = Length.add(a, b, LengthUnit.FEET);
        Length ba = Length.add(b, a, LengthUnit.FEET);
        System.out.println("Commutative check in FEET: " + ab.equals(ba) + " -> " + ab + " and " + ba);
    }

    public static void demonstrateTargetUnitAddition() {
        demonstrateLengthAddition(new Length(1.0, LengthUnit.FEET), new Length(12.0, LengthUnit.INCHES), LengthUnit.FEET);
        demonstrateLengthAddition(new Length(1.0, LengthUnit.FEET), new Length(12.0, LengthUnit.INCHES), LengthUnit.INCHES);
        Length yardsResult = demonstrateLengthAddition(new Length(1.0, LengthUnit.FEET), new Length(12.0, LengthUnit.INCHES), LengthUnit.YARDS);
        System.out.println("Rounded yard result (3 decimals): " + yardsResult.convertTo(LengthUnit.YARDS, 3));

        demonstrateLengthAddition(new Length(1.0, LengthUnit.YARDS), new Length(3.0, LengthUnit.FEET), LengthUnit.YARDS);
        demonstrateLengthAddition(new Length(36.0, LengthUnit.INCHES), new Length(1.0, LengthUnit.YARDS), LengthUnit.FEET);
        demonstrateLengthAddition(new Length(2.54, LengthUnit.CENTIMETERS), new Length(1.0, LengthUnit.INCHES), LengthUnit.CENTIMETERS);
        demonstrateLengthAddition(new Length(5.0, LengthUnit.FEET), new Length(0.0, LengthUnit.INCHES), LengthUnit.YARDS);
        demonstrateLengthAddition(new Length(5.0, LengthUnit.FEET), new Length(-2.0, LengthUnit.FEET), LengthUnit.INCHES);

        Length abYards = Length.add(new Length(1.0, LengthUnit.FEET), new Length(12.0, LengthUnit.INCHES), LengthUnit.YARDS);
        Length baYards = Length.add(new Length(12.0, LengthUnit.INCHES), new Length(1.0, LengthUnit.FEET), LengthUnit.YARDS);
        System.out.println("Commutative check in YARDS: " + abYards.equals(baYards) + " -> " + abYards + " and " + baYards);

        try {
            Length.add(new Length(1.0, LengthUnit.FEET), new Length(12.0, LengthUnit.INCHES), null);
        } catch (IllegalArgumentException ex) {
            System.out.println("Null target unit validation: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        demonstrateFeetEquality();
        demonstrateInchesEquality();
        demonstrateFeetInchesComparison();
        demonstrateExtendedUnitSupport();
        demonstrateUnitToUnitConversion();
        demonstrateLengthAdditionOperations();
        demonstrateTargetUnitAddition();
    }
}