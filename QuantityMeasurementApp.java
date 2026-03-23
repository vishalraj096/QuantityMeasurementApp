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

    public static <U extends IMeasurable> Quantity<U> demonstrateSubtraction(Quantity<U> quantity1, Quantity<U> quantity2) {
        Quantity<U> result = quantity1.subtract(quantity2);
        System.out.println("subtract(" + quantity1 + ", " + quantity2 + ") = " + result);
        return result;
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateSubtraction(Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit) {
        Quantity<U> result = quantity1.subtract(quantity2, targetUnit);
        System.out.println("subtract(" + quantity1 + ", " + quantity2 + ", " + targetUnit.getUnitName() + ") = " + result);
        return result;
    }

    public static <U extends IMeasurable> double demonstrateDivision(Quantity<U> quantity1, Quantity<U> quantity2) {
        double result = quantity1.divide(quantity2);
        System.out.println("divide(" + quantity1 + ", " + quantity2 + ") = " + result);
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
        demonstrateSubtraction(new Quantity<>(10.0, LengthUnit.FEET), new Quantity<>(6.0, LengthUnit.INCHES));
        demonstrateSubtraction(new Quantity<>(10.0, LengthUnit.FEET), new Quantity<>(6.0, LengthUnit.INCHES), LengthUnit.INCHES);
        demonstrateSubtraction(new Quantity<>(5.0, LengthUnit.FEET), new Quantity<>(10.0, LengthUnit.FEET));
        demonstrateDivision(new Quantity<>(24.0, LengthUnit.INCHES), new Quantity<>(2.0, LengthUnit.FEET));

        Quantity<WeightUnit> oneKg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> thousandGrams = new Quantity<>(1000.0, WeightUnit.GRAM);
        Quantity<WeightUnit> onePound = new Quantity<>(1.0, WeightUnit.POUND);

        System.out.println("Weight equality (1 kg == 1000 g): " + demonstrateEquality(oneKg, thousandGrams));
        System.out.println("Weight equality (1 lb == 453.592 g): " + demonstrateEquality(onePound, new Quantity<>(453.592, WeightUnit.GRAM)));

        demonstrateConversion(oneKg, WeightUnit.GRAM);
        demonstrateConversion(onePound, WeightUnit.KILOGRAM);

        demonstrateAddition(oneKg, thousandGrams);
        demonstrateAddition(oneKg, thousandGrams, WeightUnit.GRAM);
        demonstrateSubtraction(new Quantity<>(10.0, WeightUnit.KILOGRAM), new Quantity<>(5000.0, WeightUnit.GRAM));
        demonstrateSubtraction(new Quantity<>(2.0, WeightUnit.KILOGRAM), new Quantity<>(5.0, WeightUnit.KILOGRAM));
        demonstrateDivision(new Quantity<>(2000.0, WeightUnit.GRAM), new Quantity<>(1.0, WeightUnit.KILOGRAM));

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
        demonstrateSubtraction(new Quantity<>(5.0, VolumeUnit.LITRE), new Quantity<>(500.0, VolumeUnit.MILLILITRE));
        demonstrateSubtraction(new Quantity<>(5.0, VolumeUnit.LITRE), new Quantity<>(2.0, VolumeUnit.LITRE), VolumeUnit.MILLILITRE);
        demonstrateSubtraction(new Quantity<>(1.0, VolumeUnit.LITRE), new Quantity<>(1000.0, VolumeUnit.MILLILITRE));
        demonstrateDivision(new Quantity<>(1000.0, VolumeUnit.MILLILITRE), new Quantity<>(1.0, VolumeUnit.LITRE));

        Quantity<?> lengthAsAny = oneFoot;
        Quantity<?> weightAsAny = oneKg;
        Quantity<?> volumeAsAny = oneLitre;
        System.out.println("Cross-category equality (length vs weight): " + lengthAsAny.equals(weightAsAny));
        System.out.println("Cross-category equality (length vs volume): " + lengthAsAny.equals(volumeAsAny));
        System.out.println("Cross-category equality (weight vs volume): " + weightAsAny.equals(volumeAsAny));

        Quantity<TemperatureUnit> zeroCelsius = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> thirtyTwoFahrenheit = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);
        Quantity<TemperatureUnit> kelvin273_15 = new Quantity<>(273.15, TemperatureUnit.KELVIN);

        System.out.println("Temperature equality (0 C == 32 F): " + demonstrateEquality(zeroCelsius, thirtyTwoFahrenheit));
        System.out.println("Temperature equality (273.15 K == 0 C): " + demonstrateEquality(kelvin273_15, zeroCelsius));

        demonstrateConversion(new Quantity<>(100.0, TemperatureUnit.CELSIUS), TemperatureUnit.FAHRENHEIT);
        demonstrateConversion(new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT), TemperatureUnit.CELSIUS);
        demonstrateConversion(new Quantity<>(273.15, TemperatureUnit.KELVIN), TemperatureUnit.CELSIUS);

        try {
            demonstrateAddition(new Quantity<>(100.0, TemperatureUnit.CELSIUS), new Quantity<>(50.0, TemperatureUnit.CELSIUS));
        } catch (UnsupportedOperationException ex) {
            System.out.println("Unsupported operation: " + ex.getMessage());
        }

        try {
            demonstrateSubtraction(new Quantity<>(100.0, TemperatureUnit.CELSIUS), new Quantity<>(50.0, TemperatureUnit.CELSIUS));
        } catch (UnsupportedOperationException ex) {
            System.out.println("Unsupported operation: " + ex.getMessage());
        }

        try {
            demonstrateDivision(new Quantity<>(100.0, TemperatureUnit.CELSIUS), new Quantity<>(50.0, TemperatureUnit.CELSIUS));
        } catch (UnsupportedOperationException ex) {
            System.out.println("Unsupported operation: " + ex.getMessage());
        }

        Quantity<?> temperatureAsAny = zeroCelsius;
        System.out.println("Cross-category equality (temperature vs length): " + temperatureAsAny.equals(lengthAsAny));
        System.out.println("Cross-category equality (temperature vs weight): " + temperatureAsAny.equals(weightAsAny));
        System.out.println("Cross-category equality (temperature vs volume): " + temperatureAsAny.equals(volumeAsAny));
    }
}