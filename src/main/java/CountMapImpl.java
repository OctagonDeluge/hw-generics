import java.util.*;

public class CountMapImpl<K> implements CountMap<K> {

    private static class Pair<K> implements CountMap.Pair<K> {
        private final K key;
        private Integer value;
        private Pair<K> nextPair;

        public Pair(K key, Integer value) {
            this.key = key;
            this.value = value;
            nextPair = null;
        }

        public K getKey() {
            return key;
        }

        public Integer getValue() {
            return value;
        }

        public void incrValue() {
            value += 1;
        }
    }

    private Pair<K>[] table;
    private final double LOAD_FACTOR = 0.75;
    private int size;
    private int tableSize;
    private Set<CountMap.Pair<K>> pairSet;

    public CountMapImpl() {
        table = new Pair[tableSize = 16];
    }

    @Override
    public void add(K key) {
        Pair<K> pair;
        Pair<K> nextPair;
        if ((pair = table[indexFor(key)]) == null) {
            table[indexFor(key)] = new Pair<>(key, 1);
            size++;
            if (size >= tableSize * LOAD_FACTOR)
                resize();
        } else if (key.equals(table[indexFor(key)].getKey())) {
            table[indexFor(key)].incrValue();
        } else {
            for (; ; ) {
                if ((nextPair = pair.nextPair) == null) {
                    pair.nextPair = new Pair<>(key, 1);
                    size++;
                    if (size >= tableSize * LOAD_FACTOR)
                        resize();
                    break;
                }
                if (nextPair.key == key || nextPair.key.equals(key)) {
                    nextPair.incrValue();
                    break;
                }
                pair = nextPair;
            }
        }
    }

    @Override
    public void add(K key, int value) {
        Pair<K> pair;
        Pair<K> nextPair;
        if ((pair = table[indexFor(key)]) == null) {
            table[indexFor(key)] = new Pair<>(key, value);
            size++;
            if (size >= tableSize * LOAD_FACTOR)
                resize();
        } else if (key.equals(table[indexFor(key)].getKey())) {
            table[indexFor(key)].value += value;
        } else {
            for (; ; ) {
                if ((nextPair = pair.nextPair) == null) {
                    pair.nextPair = new Pair<>(key, value);
                    size++;
                    if (size >= tableSize * LOAD_FACTOR)
                        resize();
                    break;
                }
                if (nextPair.key == key || nextPair.key.equals(key)) {
                    nextPair.value += value;
                    break;
                }
                pair = nextPair;
            }
        }
    }

    @Override
    public int getCount(K key) {
        Pair<K> pair;
        Pair<K> nextPair;
        if ((pair = table[indexFor(key)]).key.equals(key)) {
            return pair.getValue();
        } else {
            for (; ; ) {
                if ((nextPair = pair.nextPair).key.equals(key)) {
                    return nextPair.getValue();
                }
                pair = nextPair;
            }
        }
    }

    @Override
    public int remove(K key) {
        Pair<K> pair;
        Pair<K> pairToRemove;
        if ((pair = table[indexFor(key)]).key.equals(key)) {
            table[indexFor(key)] = pair.nextPair;
            size--;
            return pair.getValue();
        } else {
            for (; ; ) {
                if ((pairToRemove = pair.nextPair).key.equals(key)) {
                    pair.nextPair = pairToRemove.nextPair;
                    size--;
                    return pairToRemove.getValue();
                }
                pair = pairToRemove;
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void addAll(CountMap<K> source) {
        for (CountMap.Pair<K> pair :
                source.pairSet()) {
            add(pair.getKey(), pair.getValue());
        }
    }

    @Override
    public Map<K, Integer> toMap() {
        Map<K, Integer> map = new HashMap<>();
        for (CountMap.Pair<K> pair :
                pairSet()) {
            map.put(pair.getKey(), pair.getValue());
        }
        return map;
    }

    @Override
    public void toMap(Map<K, Integer> destination) {
        for (CountMap.Pair<K> pair :
                pairSet()) {
            destination.put(pair.getKey(), pair.getValue());
        }
    }

    @Override
    public Set<CountMap.Pair<K>> pairSet() {
        pairSet = new HashSet<>();
        Pair<K> nextPair;
        for (Pair<K> pair :
                table) {
            if (pair != null) {
                pairSet.add(pair);
                while ((nextPair = pair.nextPair) != null) {
                    pairSet.add(nextPair);
                    pair = nextPair;
                }
            }
        }
        return pairSet;
    }

    private void resize() {
        table = Arrays.copyOf(table, tableSize *= 2);
        System.out.println("expansion");
    }

    private int indexFor(K key) {
        return (tableSize - 1) & key.hashCode();
    }
}
