public interface IMeasurable {
    SupportsArithmetic DEFAULT_SUPPORTS_ARITHMETIC = () -> true;

    double getConversionFactor();

    double convertToBaseUnit(double value);

    double convertFromBaseUnit(double baseValue);

    String getUnitName();

    default boolean supportsArithmetic() {
        return DEFAULT_SUPPORTS_ARITHMETIC.isSupported();
    }

    default void validateOperationSupport(String operation) {
        if (operation == null || operation.trim().isEmpty()) {
            throw new IllegalArgumentException("Operation cannot be null or empty");
        }
    }
}
