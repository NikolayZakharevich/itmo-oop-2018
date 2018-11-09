import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FractionSet implements Iterable<Fraction> {
    private FractionTreap<Fraction> elements = new FractionTreap<>();
    private Fraction minFraction, maxFraction;
    private Map<Fraction, Integer> countLess = new HashMap<>();
    private Map<Fraction, Integer> countMore = new HashMap<>();

    public void insert(Fraction fraction) {
        minFraction = null;
        maxFraction = null;
        countLess.clear();
        countMore.clear();
        elements.insert(fraction);
    }

    public Fraction getMin() {
        if (minFraction == null) {
            minFraction = elements.getMin();
        }
        return minFraction;
    }

    public Fraction getMax() {
        if (maxFraction == null) {
            maxFraction = elements.getMax();
        }
        return maxFraction;
    }

    public int countLess(Fraction fraction) {
        if (!countLess.containsKey(fraction)) {
            countLess.put(fraction, elements.countLess(fraction));
        }
        return countLess.get(fraction);
    }

    public int countMore(Fraction fraction) {
        if (!countMore.containsKey(fraction)) {
            countMore.put(fraction, elements.countMore(fraction));
        }
        return countMore.get(fraction);
    }

    public int size() {
        return elements.size();
    }

    public List<Integer> indexesOf(Fraction fraction) {
        return elements.indexesOf(fraction);
    }

    @Override
    public Iterator<Fraction> iterator() {
        return elements.iterator();
    }
}
