public final class Quantity<U extends IMeasurable> {
    private static final double EPSILON = 1e-6;

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Quantity value must be a finite number");
        }
        if (unit == null) {
            throw new IllegalArgumentException("Quantity unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    private double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double baseValue = toBaseUnit();
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);
        return new Quantity<>(convertedValue, targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        if (other == null) {
            throw new IllegalArgumentException("Other quantity cannot be null");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double sumInBase = this.toBaseUnit() + other.toBaseUnit();
        if (!Double.isFinite(sumInBase)) {
            throw new IllegalArgumentException("Sum is out of range");
        }

        double sumInTarget = targetUnit.convertFromBaseUnit(sumInBase);
        return new Quantity<>(sumInTarget, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Quantity<?>)) {
            return false;
        }

        Quantity<?> other = (Quantity<?>) obj;
        if (this.unit.getClass() != other.unit.getClass()) {
            return false;
        }

        return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < EPSILON;
    }

    @Override
    public int hashCode() {
        double normalizedBase = Math.round(toBaseUnit() / EPSILON) * EPSILON;
        int result = unit.getClass().hashCode();
        result = 31 * result + Double.hashCode(normalizedBase);
        return result;
    }

    @Override
    public String toString() {
        return String.format("Quantity[value=%.6f, unit=%s]", value, unit.getUnitName());
    }
}
