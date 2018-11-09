public class Fraction implements Comparable<Fraction> {
    private int n, m;
    private double value;

    public Fraction() {
        value = 0;
    }

    public Fraction(int n, int m) {
        if (m == 0) {
            throw new IllegalArgumentException("Argument 'm' is 0");
        } else {
            this.n = n;
            this.m = m;
            value = (double) n / m;
        }
    }

    public double getValue() {
        return value;
    }

    @Override
    public int compareTo(Fraction fraction) {
        return Double.compare(value, fraction.getValue());
    }

    public String toString() {
        return String.format("%." + 3 + "f", value);
    }
}
