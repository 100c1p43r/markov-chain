package dev.a100c1p43r.markov.text;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

class RandomMock extends Random {
    private Iterator<Integer> iterator;

    RandomMock(Collection<Integer> collection) {
        this.iterator = collection.iterator();
    }

    @Override
    public int nextInt(int bound) {
        return iterator.next();
    }
}
