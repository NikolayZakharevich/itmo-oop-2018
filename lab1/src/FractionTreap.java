import java.util.List;

import java.util.Iterator;

public class FractionTreap<T extends Comparable> extends Treap<T> implements Iterable<T> {
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            TreapNode<T> currentNode;
            int currentPosition = 0;

            @Override
            public boolean hasNext() {
                if (currentNode == null) {
                    return root != null;
                }
                if (currentPosition < currentNode.nElements - 1) {
                    return true;
                }
                return findNext(root, currentNode.value) != null;
            }

            @Override
            public T next() {
                if (currentNode != null && currentPosition < currentNode.nElements - 1) {
                    currentPosition++;
                } else {
                    currentPosition = 0;
                    currentNode = currentNode == null ? getMin(root) : findNext(root, currentNode.value);
                }
                return currentNode.value;
            }
        };
    }

    @Override
    public void insert(T value) {
        TreapNode<T> inserted = find(root, value);
        if (inserted != null) {
            inserted.indexes.add(size());
            inserted.nElements++;
            inserted.weight++;
            relaxFromNode(root, value);
        } else {
            super.insert(value);

        }
    }

    public List<Integer> indexesOf(T value) {
        TreapNode<T> ret = find(root, value);
        return ret.indexes;
    }
}
