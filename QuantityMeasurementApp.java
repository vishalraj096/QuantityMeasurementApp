public final class QuantityMeasurementApp {
    private static QuantityMeasurementApp instance;

    private final IQuantityMeasurementRepository repository;
    private final QuantityMeasurementController controller;

    private QuantityMeasurementApp() {
        this.repository = createRepository();
        IQuantityMeasurementService service = createQuantityMeasurementService(repository);
        this.controller = createQuantityMeasurementController(service);
    }

    public static synchronized QuantityMeasurementApp getInstance() {
        if (instance == null) {
            instance = new QuantityMeasurementApp();
        }
        return instance;
    }

    private static IQuantityMeasurementRepository createRepository() {
        return QuantityMeasurementCacheRepository.getInstance();
    }

    private static IQuantityMeasurementService createQuantityMeasurementService(IQuantityMeasurementRepository repository) {
        return new QuantityMeasurementServiceImpl(repository);
    }

    private static QuantityMeasurementController createQuantityMeasurementController(IQuantityMeasurementService service) {
        return new QuantityMeasurementController(service);
    }

    public void runDemonstrations() {
        QuantityDTO length1 = new QuantityDTO(1.0, LengthUnit.FEET);
        QuantityDTO length2 = new QuantityDTO(12.0, LengthUnit.INCHES);
        QuantityDTO inchesTarget = new QuantityDTO(0.0, LengthUnit.INCHES);

        System.out.println("Length compare result: " + controller.performComparison(length1, length2));
        System.out.println("Length conversion result: " + controller.performConversion(length1, inchesTarget));
        System.out.println("Length addition result: " + controller.performAddition(length1, length2));

        QuantityDTO volume1 = new QuantityDTO(1.0, VolumeUnit.LITRE);
        QuantityDTO volume2 = new QuantityDTO(500.0, VolumeUnit.MILLILITRE);
        System.out.println("Volume subtraction result: " + controller.performSubtraction(volume1, volume2));

        QuantityDTO weight1 = new QuantityDTO(2.0, WeightUnit.KILOGRAM);
        QuantityDTO weight2 = new QuantityDTO(1000.0, WeightUnit.GRAM);
        System.out.println("Weight division result: " + controller.performDivision(weight1, weight2));

        QuantityDTO temp1 = new QuantityDTO(0.0, TemperatureUnit.CELSIUS);
        QuantityDTO temp2 = new QuantityDTO(32.0, TemperatureUnit.FAHRENHEIT);
        QuantityDTO tempTarget = new QuantityDTO(0.0, TemperatureUnit.CELSIUS);
        System.out.println("Temperature compare result: " + controller.performComparison(temp1, temp2));
        System.out.println("Temperature conversion result: " + controller.performConversion(temp2, tempTarget));

        try {
            controller.performAddition(temp1, temp2, tempTarget);
        } catch (QuantityMeasurementException ex) {
            System.out.println("Temperature addition not supported: " + ex.getMessage());
        }

        System.out.println("Stored operation count: " + repository.getAllMeasurements().size());
    }

    public static void main(String[] args) {
        getInstance().runDemonstrations();
    }
}