import java.util.*;

public class FractionVector {

    final private List<Fraction> elements = new ArrayList<>();
    private Fraction minFraction, maxFraction;
    private Map<Fraction, Integer> countLess = new HashMap<>();
    private Map<Fraction, Integer> countMore = new HashMap<>();

    public void insert(Fraction fraction) {
        minFraction = null;
        maxFraction = null;
        elements.add(fraction);
    }

    public Fraction getMin() {
        if (minFraction == null) {
            minFraction = Collections.min(elements, Comparator.comparingDouble(Fraction::getValue));
        }
        return minFraction;
    }

    public Fraction getMax() {
        if (maxFraction == null) {
            maxFraction = Collections.min(elements, Comparator.comparingDouble(Fraction::getValue));
        }
        return maxFraction;
    }

    private int count(Fraction fraction, Comparator<Fraction> comparator) {
        int count = 0;
        for (Fraction elem : elements) {
            if (comparator.compare(fraction, elem) > 0) {
                count++;
            }
        }
        return count;
    }

    private class ValueComparator implements Comparator<Fraction> {
        private boolean isReversed;

        ValueComparator() {
        }

        ValueComparator(boolean isReversed) {
            this.isReversed = isReversed;
        }

        @Override
        public int compare(Fraction a, Fraction b) {
            return isReversed
                    ? Double.compare(b.getValue(), a.getValue())
                    : Double.compare(a.getValue(), b.getValue());
        }
    }

    public int countLess(Fraction fraction) {
        if (!countLess.containsKey(fraction)) {
            countLess.put(fraction, count(fraction, new ValueComparator()));
        }
        return countLess.get(fraction);
    }

    public int countMore(Fraction fraction) {

        if (!countMore.containsKey(fraction)) {
            countMore.put(fraction, count(fraction, new ValueComparator(true)));
        }
        return countMore.get(fraction);
    }

}
