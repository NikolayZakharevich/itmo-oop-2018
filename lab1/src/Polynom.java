import java.util.ArrayList;
import java.util.Collections;


public class Polynom {
    private ArrayList<Fraction> coefficients;

    Polynom(FractionSet fractionSet) {
        coefficients = new ArrayList<>(Collections.nCopies(fractionSet.size(), new Fraction()));
        Fraction prev = null;
        for (Fraction fraction : fractionSet) {
            if (prev == null || prev != fraction) {
                for (int index : fractionSet.indexesOf(fraction)) {
                    coefficients.set(index, fraction);
                }
                prev = fraction;
            }

        }
    }

    public void print() {
        for (Fraction coefficient : coefficients) {
            System.out.print(coefficient + " ");
        }
        System.out.println();
    }

    public double getSum(Fraction value) {
        double sum = 0;
        for (Fraction coefficient : coefficients) {
            sum += coefficient.getValue();
            sum *= value.getValue();
        }
        return sum;
    }

    public void printSum(Fraction value) {
        double sum = 0;
        int power = coefficients.size() - 1;
        for (Fraction coefficient : coefficients) {
            System.out.print(coefficient + "*" + value + "^" + power + (power != 0 ? " + " : " = "));
            sum += coefficient.getValue();
            sum *= value.getValue();
            power--;
        }
        System.out.println(sum);
    }
}
