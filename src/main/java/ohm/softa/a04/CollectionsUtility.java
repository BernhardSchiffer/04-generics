package ohm.softa.a04;

import java.util.Comparator;
import java.util.Iterator;

public abstract class CollectionsUtility {

    public static <T> SimpleList<T> sort(SimpleList<T> list, Comparator<T> cmp) {
        SimpleList<T> left = getLeft(list);
        SimpleList<T> right = getRight(list);

        return merge(left, right, cmp);
    }

    private static <T> SimpleList<T> merge(SimpleList<T> left, SimpleList<T> right, Comparator<T> cmp) {
        SimpleList<T> result = new SimpleListImpl<>();

        Iterator<T> leftIterator = left.iterator();
        Iterator<T> rightIterator = right.iterator();

        T leftElement = null;
        T rightElement = null;

        while (leftIterator.hasNext() || rightIterator.hasNext()) {
            if(leftElement == null && leftIterator.hasNext()) {
                leftElement = leftIterator.next();
            }
            if(rightElement == null && rightIterator.hasNext()) {
                rightElement = rightIterator.next();
            }
            if (rightElement != null && leftElement != null) {
                if(cmp.compare(leftElement, rightElement) < 0) {
                    result.add(leftElement);
                    leftElement = null;
                }
                else if (cmp.compare(leftElement, rightElement) > 0){
                    result.add(rightElement);
                    rightElement = null;
                }
                else {
                    result.add(rightElement);
                    rightElement = null;
                    result.add(leftElement);
                    leftElement = null;
                }
            }
            else if(leftElement == null) {
                result.add(rightElement);
                rightElement = null;
            }
            else {
                result.add(leftElement);
                leftElement = null;
            }

        }

        return result;
    }

    private static <T> SimpleList<T> getLeft(SimpleList<T> list) {
        SimpleList<T> result = new SimpleListImpl<>();
        Iterator<T> iterator = list.iterator();
        int size = list.size();
        int i = 1;
        while (iterator.hasNext() && i <= size/2) {
            result.add(iterator.next());
            i++;
        }
        return result;
    }

    private static <T> SimpleList<T> getRight(SimpleList<T> list) {
        SimpleList<T> result = new SimpleListImpl<>();
        Iterator<T> iterator = list.iterator();
        int size = list.size();
        int i = 1;
        while (iterator.hasNext()) {
            if(i > size/2) {
                result.add(iterator.next());
                i++;
            }
            else {
                iterator.next();
                i++;
            }
        }
        return result;
    }

}
