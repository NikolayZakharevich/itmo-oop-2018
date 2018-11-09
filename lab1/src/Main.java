import java.io.File;

public class Main {

    public static void main(String[] args) {
        InputReader in = new InputReader(new File("input.txt"));
        FractionSet fractionSet = new FractionSet();
        int nFractions = in.nextInt();
        for (int i = 0; i < nFractions; i++) {
            int n = in.nextInt();
            int m = in.nextInt();
            fractionSet.insert(new Fraction(n, m));
        }

        System.out.println("min = " + fractionSet.getMax());
        System.out.println("max = " + fractionSet.getMin());

        System.out.println("fractions more than 1 = " + fractionSet.countMore(new Fraction(1, 1)));
        System.out.println("fractions less than 1 = " + fractionSet.countLess(new Fraction(1, 1)));

        Polynom polynom = new Polynom(fractionSet);
        polynom.print();

        System.out.println(polynom.getSum(new Fraction(1, 1)));
        polynom.printSum(new Fraction(1, 1));
    }
}
