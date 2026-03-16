public class QuantityMeasurementApp {
    public static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            Feet temp = (Feet) obj;
            return Double.compare(this.value, temp.value) == 0;
        }
    }

    public static void main(String[] args) {
        double val1 = 6.4;
        double val2 = 6.4;

        Feet obj1 = new Feet(val1);
        Feet obj2 = new Feet(val2);

        if (obj1.equals(obj2))
            System.out.println("Both are equal in feet");
        else
            System.out.println("Values are different");
    }
}