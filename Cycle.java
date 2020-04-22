package midterm.uno;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * this class create a round list
 * @param <T> type of class will be added to cycle
 */
public class Cycle<T> implements Iterable<T> {

    private LinkedList<T> list = new LinkedList<>();

    /**
     * add a new member to cycle
     * @param o member to be added
     */
    public void add(T o) {
        list.addLast(o);
    }

    /**
     * return the last member of cycle
     * @return T last member
     */
    public T getLast() {
        return list.get(list.size() - 1);
    }

    /**
     * gets the size of list
     * @return int size of list
     */
    public int size() {
        return list.size();
    }

    @Override
    public ListIterator<T> iterator() {
        return new ListIterator<T>() {

            int index = -1;

            @Override
            public boolean hasNext() {
                if (list.size() == 0)
                    return false;
                return true;
            }

            @Override
            public T next() {
                if (list.size() == 0)
                    return null;
                else {
                    if (index == list.size() - 1) {
                        index = 0;
                        return list.get(0);
                    }
                    else {
                        return list.get(++index);
                    }
                }
            }

            @Override
            public boolean hasPrevious() {
                if (list.size() == 0)
                    return false;
                return true;
            }

            @Override
            public T previous() {
                if (list.size() == 0)
                    return null;
                else {
                    if (index == 0 || index == -1) {
                        index = list.size() - 1;
                        return list.get(index);
                    }
                    else {
                        return list.get(--index);
                    }
                }
            }

            @Override
            public int nextIndex() {
                if (index == list.size() - 1)
                    return 0;
                else {
                    return index + 1;
                }
            }

            @Override
            public int previousIndex() {
                if (index == 0) {
                    return list.size() - 1;
                }
                else {
                    return index - 1;
                }
            }

            @Override
            public void remove() {
                list.remove(index);
            }

            @Override
            public void set(T t) {
                list.remove(index);
                list.add(index, t);
            }

            @Override
            public void add(T t) {
                list.addLast(t);
            }
        };
    }
}