public class QuantityMeasurementApp {
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

    public static void demonstrateStandaloneUnitConversionResponsibility() {
        System.out.println("LengthUnit.FEET.convertToBaseUnit(12.0) = " + LengthUnit.FEET.convertToBaseUnit(12.0));
        System.out.println("LengthUnit.INCHES.convertToBaseUnit(12.0) = " + LengthUnit.INCHES.convertToBaseUnit(12.0));
        System.out.println("LengthUnit.YARDS.convertToBaseUnit(1.0) = " + LengthUnit.YARDS.convertToBaseUnit(1.0));
        System.out.println("LengthUnit.CENTIMETERS.convertToBaseUnit(30.48) = " + LengthUnit.CENTIMETERS.convertToBaseUnit(30.48));

        System.out.println("LengthUnit.FEET.convertFromBaseUnit(2.0) = " + LengthUnit.FEET.convertFromBaseUnit(2.0));
        System.out.println("LengthUnit.INCHES.convertFromBaseUnit(1.0) = " + LengthUnit.INCHES.convertFromBaseUnit(1.0));
        System.out.println("LengthUnit.YARDS.convertFromBaseUnit(3.0) = " + LengthUnit.YARDS.convertFromBaseUnit(3.0));
        System.out.println("LengthUnit.CENTIMETERS.convertFromBaseUnit(1.0) = " + LengthUnit.CENTIMETERS.convertFromBaseUnit(1.0));
    }

    public static boolean demonstrateWeightEquality(Weight weight1, Weight weight2) {
        return weight1.equals(weight2);
    }

    public static boolean demonstrateWeightComparison(double value1, WeightUnit unit1, double value2, WeightUnit unit2) {
        Weight weight1 = new Weight(value1, unit1);
        Weight weight2 = new Weight(value2, unit2);
        return demonstrateWeightEquality(weight1, weight2);
    }

    public static Weight demonstrateWeightConversion(double value, WeightUnit fromUnit, WeightUnit toUnit) {
        Weight source = new Weight(value, fromUnit);
        Weight converted = source.convertTo(toUnit);
        System.out.println("weight.convert(" + value + ", " + fromUnit + ", " + toUnit + ") = " + converted.getValue());
        return converted;
    }

    public static Weight demonstrateWeightConversion(Weight weight, WeightUnit toUnit) {
        Weight converted = weight.convertTo(toUnit);
        System.out.println("weight.convert(" + weight.getValue() + ", " + weight.getUnit() + ", " + toUnit + ") = " + converted.getValue());
        return converted;
    }

    public static Weight demonstrateWeightAddition(Weight weight1, Weight weight2) {
        Weight result = weight1.add(weight2);
        System.out.println("weight.add(" + weight1 + ", " + weight2 + ") = " + result);
        return result;
    }

    public static Weight demonstrateWeightAddition(Weight weight1, Weight weight2, WeightUnit targetUnit) {
        Weight result = Weight.add(weight1, weight2, targetUnit);
        System.out.println("weight.add(" + weight1 + ", " + weight2 + ", " + targetUnit + ") = " + result);
        return result;
    }

    public static void demonstrateWeightUnitConversionResponsibility() {
        System.out.println("WeightUnit.KILOGRAM.convertToBaseUnit(1.0) = " + WeightUnit.KILOGRAM.convertToBaseUnit(1.0));
        System.out.println("WeightUnit.GRAM.convertToBaseUnit(1000.0) = " + WeightUnit.GRAM.convertToBaseUnit(1000.0));
        System.out.println("WeightUnit.POUND.convertToBaseUnit(2.20462) = " + WeightUnit.POUND.convertToBaseUnit(2.20462));

        System.out.println("WeightUnit.KILOGRAM.convertFromBaseUnit(1.0) = " + WeightUnit.KILOGRAM.convertFromBaseUnit(1.0));
        System.out.println("WeightUnit.GRAM.convertFromBaseUnit(1.0) = " + WeightUnit.GRAM.convertFromBaseUnit(1.0));
        System.out.println("WeightUnit.POUND.convertFromBaseUnit(1.0) = " + WeightUnit.POUND.convertFromBaseUnit(1.0));
    }

    public static void demonstrateWeightEqualityOperations() {
        System.out.println("1 kg == 1 kg ? " + demonstrateWeightComparison(1.0, WeightUnit.KILOGRAM, 1.0, WeightUnit.KILOGRAM));
        System.out.println("1 kg == 1000 g ? " + demonstrateWeightComparison(1.0, WeightUnit.KILOGRAM, 1000.0, WeightUnit.GRAM));
        System.out.println("2 lb == 2 lb ? " + demonstrateWeightComparison(2.0, WeightUnit.POUND, 2.0, WeightUnit.POUND));
        double oneKgInPounds = WeightUnit.POUND.convertFromBaseUnit(1.0);
        System.out.println("1 kg == ~2.20462 lb ? " + demonstrateWeightComparison(1.0, WeightUnit.KILOGRAM, oneKgInPounds, WeightUnit.POUND));
        System.out.println("500 g == 0.5 kg ? " + demonstrateWeightComparison(500.0, WeightUnit.GRAM, 0.5, WeightUnit.KILOGRAM));
        System.out.println("1 lb == 453.592 g ? " + demonstrateWeightComparison(1.0, WeightUnit.POUND, 453.592, WeightUnit.GRAM));
    }

    public static void demonstrateWeightConversionOperations() {
        demonstrateWeightConversion(1.0, WeightUnit.KILOGRAM, WeightUnit.GRAM);
        demonstrateWeightConversion(2.0, WeightUnit.POUND, WeightUnit.KILOGRAM);
        demonstrateWeightConversion(500.0, WeightUnit.GRAM, WeightUnit.POUND);
        demonstrateWeightConversion(0.0, WeightUnit.KILOGRAM, WeightUnit.GRAM);
    }

    public static void demonstrateWeightAdditionOperations() {
        demonstrateWeightAddition(new Weight(1.0, WeightUnit.KILOGRAM), new Weight(2.0, WeightUnit.KILOGRAM));
        demonstrateWeightAddition(new Weight(1.0, WeightUnit.KILOGRAM), new Weight(1000.0, WeightUnit.GRAM));
        demonstrateWeightAddition(new Weight(500.0, WeightUnit.GRAM), new Weight(0.5, WeightUnit.KILOGRAM));

        demonstrateWeightAddition(new Weight(1.0, WeightUnit.KILOGRAM), new Weight(1000.0, WeightUnit.GRAM), WeightUnit.GRAM);
        demonstrateWeightAddition(new Weight(1.0, WeightUnit.POUND), new Weight(453.592, WeightUnit.GRAM), WeightUnit.POUND);
        demonstrateWeightAddition(new Weight(2.0, WeightUnit.KILOGRAM), new Weight(4.0, WeightUnit.POUND), WeightUnit.KILOGRAM);

        Weight ab = Weight.add(new Weight(1.0, WeightUnit.KILOGRAM), new Weight(1000.0, WeightUnit.GRAM), WeightUnit.POUND);
        Weight ba = Weight.add(new Weight(1000.0, WeightUnit.GRAM), new Weight(1.0, WeightUnit.KILOGRAM), WeightUnit.POUND);
        System.out.println("Weight commutative check in POUND: " + ab.equals(ba) + " -> " + ab + " and " + ba);
    }

    public static void demonstrateCategoryTypeSafety() {
        Weight oneKg = new Weight(1.0, WeightUnit.KILOGRAM);
        Object oneFootAsObject = new Length(1.0, LengthUnit.FEET);
        System.out.println("Weight vs Length equality (must be false): " + oneKg.equals(oneFootAsObject));
    }

    public static void main(String[] args) {
        demonstrateFeetEquality();
        demonstrateInchesEquality();
        demonstrateFeetInchesComparison();
        demonstrateExtendedUnitSupport();
        demonstrateUnitToUnitConversion();
        demonstrateLengthAdditionOperations();
        demonstrateTargetUnitAddition();
        demonstrateStandaloneUnitConversionResponsibility();

        demonstrateWeightUnitConversionResponsibility();
        demonstrateWeightEqualityOperations();
        demonstrateWeightConversionOperations();
        demonstrateWeightAdditionOperations();
        demonstrateCategoryTypeSafety();
    }
}