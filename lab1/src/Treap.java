import javafx.util.Pair;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Treap<T extends Comparable> implements Iterable<T> {
    protected class TreapNode<T> {
        T value;
        int priority;
        int weight = 1;
        TreapNode<T> left;
        TreapNode<T> right;
        int nElements = 1;
        List<Integer> indexes = new ArrayList<>();

        TreapNode(T value, int index) {
            this.value = value;
            indexes.add(index);
            priority = new Random().nextInt();
        }
    }

    protected TreapNode<T> root;
    private boolean splitMethodIgnoresValueFlag = true;

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            TreapNode<T> currentNode;

            @Override
            public boolean hasNext() {
                if (currentNode == null) {
                    return root != null;
                }
                return findNext(root, currentNode.value) != null;
            }

            @Override
            public T next() {
                currentNode = currentNode == null ? getMin(root) : findNext(root, currentNode.value);
                return currentNode.value;
            }
        };
    }

    private void relax(TreapNode<T> t) {
        t.weight = (t.left != null ? t.left.weight : 0) + (t.right != null ? t.right.weight : 0) + 1;
    }

    protected void relaxFromNode(TreapNode<T> t, T value) {
        if (t == null) {
            return;
        }
        int compareResult = t.value.compareTo(value);
        if (compareResult < 0) {
            relaxFromNode(t.right, value);
            relax(t);
        } else if (compareResult > 0) {
            relaxFromNode(t.left, value);
            relax(t);
        }
    }

    private TreapNode<T> merge(TreapNode<T> l, TreapNode<T> r) {
        if (l == null) {
            return r;
        }
        if (r == null) {
            return l;
        }
        if (l.priority < r.priority) {
            l.right = merge(l.right, r);
            relax(l);
            return l;
        } else {
            r.left = merge(l, r.left);
            relax(r);
            return r;
        }
    }

    private Pair<TreapNode<T>, TreapNode<T>> split(TreapNode<T> t, T x, boolean ignoreValue) {
        splitMethodIgnoresValueFlag = ignoreValue;
        Pair<TreapNode<T>, TreapNode<T>> splitTreaps = split(t, x);
        splitMethodIgnoresValueFlag = true;
        return splitTreaps;
    }

    private Pair<TreapNode<T>, TreapNode<T>> split(TreapNode<T> t, T x) {
        if (t == null) {
            return new Pair<>(null, null);
        }
        int compareResult = t.value.compareTo(x);
        if (compareResult < 0) {
            Pair<TreapNode<T>, TreapNode<T>> lr = split(t.right, x);
            t.right = lr.getKey();
            relax(t);
            return new Pair<>(t, lr.getValue());
        } else {
            if (compareResult == 0 && !splitMethodIgnoresValueFlag) {
                return new Pair<>(t.left, t.right);
            } else {
                Pair<TreapNode<T>, TreapNode<T>> lr = split(t.left, x);
                t.left = lr.getValue();
                relax(t);
                return new Pair<>(lr.getKey(), t);
            }
        }
    }

    protected TreapNode<T> find(TreapNode<T> t, T value) {
        if (t == null) {
            return null;
        }
        int compareResult = t.value.compareTo(value);
        if (compareResult < 0) {
            return find(t.right, value);
        } else if (compareResult > 0) {
            return find(t.left, value);
        } else {
            return t;
        }
    }

    protected TreapNode<T> findNext(TreapNode<T> t, T value) {
        TreapNode<T> curr = t, successor = null;
        while (curr != null) {
            int compareResult = curr.value.compareTo(value);
            if (compareResult > 0) {
                successor = curr;
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        return successor;
    }


    protected TreapNode<T> getMin(TreapNode<T> t) {
        if (t.left == null) {
            return t;
        }
        return getMin(t.left);
    }

    protected TreapNode<T> getMax(TreapNode<T> t) {
        if (t.right == null) {
            return t;
        }
        return getMax(t.right);
    }

    public void insert(T value) {
        int index = size();
        Pair<TreapNode<T>, TreapNode<T>> lr = split(root, value, false);
        root = merge(merge(lr.getKey(), new TreapNode<>(value, index)), lr.getValue());
    }

    public void remove(T value) {
        Pair<TreapNode<T>, TreapNode<T>> lr = split(root, value, false);
        root = merge(lr.getKey(), lr.getValue());
    }

    public int size() {
        return root != null ? root.weight : 0;
    }

    public int countLess(T value) {
        int count = size();
        Pair<TreapNode<T>, TreapNode<T>> lr = split(root, value);
        count -= (lr.getValue() != null ? lr.getValue().weight : 0);
        merge(lr.getKey(), lr.getValue());
        return count;
    }

    public int countMore(T value) {
        int count = size();
        Pair<TreapNode<T>, TreapNode<T>> lr = split(root, value);
        count -= (lr.getKey() != null ? lr.getKey().weight : 0);
        merge(lr.getKey(), lr.getValue());
        return count;
    }

    public T getMin() {
        return getMin(root).value;
    }

    public T getMax() {
        return getMax(root).value;
    }
}
