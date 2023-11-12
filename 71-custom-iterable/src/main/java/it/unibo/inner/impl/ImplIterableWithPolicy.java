package it.unibo.inner.impl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class ImplIterableWithPolicy<T> implements IterableWithPolicy<T> {

    private final T[] elements;
    private Predicate<T> iteratorFilter;

    public ImplIterableWithPolicy(final T[] array, Predicate<T> filter) {
        this.elements = Arrays.copyOf(array, array.length);
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
    public void setIterationPolicy(Predicate<T> filter) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setIterationPolicy'");
    }
    
    class MyIterator implements Iterator<T> {

        private int i=0;

        public boolean hasNext() {
            return i < elements.length-1;
        }

        public T next() {
            if (this.hasNext()){
                return elements[i++];
            }
            throw new NoSuchElementException();
        }

    }
}
