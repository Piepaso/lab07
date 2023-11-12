package it.unibo.inner.impl;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class ImplIterableWithPolicy<T> implements IterableWithPolicy<T> {

    private final List<T> elements;
    private Predicate<T> iteratorFilter;

    public ImplIterableWithPolicy(final T[] array, Predicate<T> filter) {
        this.elements = List.of(array);
        this.iteratorFilter = filter;
    }

    public ImplIterableWithPolicy(final T[] array) {

        this(array, new Predicate<T>() {
            public boolean test(T elem) {
                return true;
            }           
        });
    }

    @Override
    public Iterator<T> iterator() {
        return this.new MyIterator();
    }

    @Override
    public void setIterationPolicy(final Predicate<T> filter) {
        this.iteratorFilter = filter;
    }

    @Override
    public String toString() {
        String out="[";
        Iterator<T> it = this.iterator();
        while (it.hasNext()) {
            out = out + it.next().toString() + ", ";
        }
        out = out + "]";
        return out;
    }
    
    class MyIterator implements Iterator<T> {

        private int i=0;

        public boolean hasNext() {
            while (i < elements.size()) {
                if (iteratorFilter.test(elements.get(i))) {
                    return true;
                }
                i++;
            }
            return false;
        }

        public T next() {
            if (this.hasNext()){
                    return elements.get(i++);
            }
            throw new NoSuchElementException("La lista è finita!");
        }

    }
}
