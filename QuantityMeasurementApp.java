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

        private double toFeet() {
            return value * unit.getToFeetFactor();
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

    public static void main(String[] args) {
        demonstrateFeetEquality();
        demonstrateInchesEquality();
        demonstrateFeetInchesComparison();
        demonstrateExtendedUnitSupport();
    }
}