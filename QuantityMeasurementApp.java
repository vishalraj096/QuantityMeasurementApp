public class QuantityMeasurementApp {
    public static <U extends IMeasurable> boolean demonstrateEquality(Quantity<U> quantity1, Quantity<U> quantity2) {
        return quantity1.equals(quantity2);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateConversion(Quantity<U> quantity, U targetUnit) {
        Quantity<U> converted = quantity.convertTo(targetUnit);
        System.out.println("convert(" + quantity + ", " + targetUnit.getUnitName() + ") = " + converted);
        return converted;
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> quantity1, Quantity<U> quantity2) {
        Quantity<U> result = quantity1.add(quantity2);
        System.out.println("add(" + quantity1 + ", " + quantity2 + ") = " + result);
        return result;
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit) {
        Quantity<U> result = quantity1.add(quantity2, targetUnit);
        System.out.println("add(" + quantity1 + ", " + quantity2 + ", " + targetUnit.getUnitName() + ") = " + result);
        return result;
    }

    public static void main(String[] args) {
        Quantity<LengthUnit> oneFoot = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> twelveInches = new Quantity<>(12.0, LengthUnit.INCHES);
        Quantity<LengthUnit> oneYard = new Quantity<>(1.0, LengthUnit.YARDS);
        Quantity<LengthUnit> cm30_48 = new Quantity<>(30.48, LengthUnit.CENTIMETERS);

        System.out.println("Length equality (1 foot == 12 inches): " + demonstrateEquality(oneFoot, twelveInches));
        System.out.println("Length equality (1 yard == 36 inches): " + demonstrateEquality(oneYard, new Quantity<>(36.0, LengthUnit.INCHES)));
        System.out.println("Length equality (30.48 cm == 1 foot): " + demonstrateEquality(cm30_48, oneFoot));

        demonstrateConversion(oneFoot, LengthUnit.INCHES);
        demonstrateConversion(oneYard, LengthUnit.FEET);
        demonstrateConversion(cm30_48, LengthUnit.INCHES);

        demonstrateAddition(oneFoot, twelveInches);
        demonstrateAddition(oneFoot, twelveInches, LengthUnit.INCHES);
        demonstrateAddition(oneFoot, twelveInches, LengthUnit.YARDS);

        Quantity<WeightUnit> oneKg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> thousandGrams = new Quantity<>(1000.0, WeightUnit.GRAM);
        Quantity<WeightUnit> onePound = new Quantity<>(1.0, WeightUnit.POUND);

        System.out.println("Weight equality (1 kg == 1000 g): " + demonstrateEquality(oneKg, thousandGrams));
        System.out.println("Weight equality (1 lb == 453.592 g): " + demonstrateEquality(onePound, new Quantity<>(453.592, WeightUnit.GRAM)));

        demonstrateConversion(oneKg, WeightUnit.GRAM);
        demonstrateConversion(onePound, WeightUnit.KILOGRAM);

        demonstrateAddition(oneKg, thousandGrams);
        demonstrateAddition(oneKg, thousandGrams, WeightUnit.GRAM);

        Quantity<VolumeUnit> oneLitre = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> thousandMillilitres = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> oneGallon = new Quantity<>(1.0, VolumeUnit.GALLON);

        System.out.println("Volume equality (1 litre == 1000 millilitre): " + demonstrateEquality(oneLitre, thousandMillilitres));
        System.out.println("Volume equality (1 gallon == 3.78541 litre): " + demonstrateEquality(oneGallon, new Quantity<>(3.78541, VolumeUnit.LITRE)));

        demonstrateConversion(oneLitre, VolumeUnit.MILLILITRE);
        demonstrateConversion(oneGallon, VolumeUnit.LITRE);
        demonstrateConversion(thousandMillilitres, VolumeUnit.GALLON);

        demonstrateAddition(oneLitre, thousandMillilitres);
        demonstrateAddition(oneLitre, thousandMillilitres, VolumeUnit.MILLILITRE);
        demonstrateAddition(oneLitre, oneGallon, VolumeUnit.GALLON);

        Quantity<?> lengthAsAny = oneFoot;
        Quantity<?> weightAsAny = oneKg;
        Quantity<?> volumeAsAny = oneLitre;
        System.out.println("Cross-category equality (length vs weight): " + lengthAsAny.equals(weightAsAny));
        System.out.println("Cross-category equality (length vs volume): " + lengthAsAny.equals(volumeAsAny));
        System.out.println("Cross-category equality (weight vs volume): " + weightAsAny.equals(volumeAsAny));
    }
}