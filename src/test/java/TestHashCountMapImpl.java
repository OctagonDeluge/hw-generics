import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

public class TestHashCountMapImpl {

    CountMap<Integer> intMap = new CountMapImpl<>();
    CountMap<String> stringMap = new CountMapImpl<>();

    @Test
    void testAddiGetCount() {
        intMap.add(10);
        intMap.add(10);
        intMap.add(5);
        intMap.add(6);
        intMap.add(5);
        intMap.add(10);
        Assertions.assertEquals(3, intMap.getCount(10));
        Assertions.assertEquals(2, intMap.getCount(5));
        Assertions.assertEquals(1, intMap.getCount(6));
    }

    @Test
    void testRemove() {
        intMap.add(10);
        intMap.add(10);
        intMap.add(5);
        intMap.add(6);
        intMap.add(5);
        intMap.add(10);
        Assertions.assertEquals(3, intMap.remove(10));
        Assertions.assertEquals(2, intMap.remove(5));
    }

    @Test
    void testSize() {
        intMap.add(10);
        intMap.add(10);
        intMap.add(5);
        intMap.add(6);
        intMap.add(5);
        intMap.add(10);
        Assertions.assertEquals(3, intMap.size());
    }

    @Test
    void testPairSet() {
        stringMap.add("BBaA");
        stringMap.add("AaBB");
        stringMap.add("BaAB");
        stringMap.add("BaAB");
        stringMap.add("BBAa");
        for (CountMap.Pair<String> pair :
                stringMap.pairSet()) {
            System.out.println(pair.getKey() + " = " + pair.getValue());
        }
    }

    @Test
    void testAddAll() {
        CountMap<Integer> source = new CountMapImpl<>();
        source.add(10);
        source.add(10);
        source.add(10);
        source.add(5);
        source.add(5);
        source.add(7);
        source.add(6);
        intMap.add(5);
        intMap.add(10);
        intMap.addAll(source);
        Assertions.assertEquals(4, intMap.size());
        Assertions.assertEquals(4, intMap.getCount(10));
        Assertions.assertEquals(3, intMap.getCount(5));
        Assertions.assertEquals(1, intMap.getCount(6));
        Assertions.assertEquals(1, intMap.getCount(7));
    }

    @Test
    void testToMap() {
        intMap.add(10);
        intMap.add(10);
        intMap.add(5);
        intMap.add(6);
        intMap.add(5);
        intMap.add(10);

        System.out.println(intMap.toMap().toString());
    }

    @Test
    void testToMapWithDest() {
        Map<Integer, Integer> hashMap = new HashMap<>();
        intMap.add(10);
        intMap.add(10);
        intMap.add(5);
        intMap.add(6);
        intMap.add(5);
        intMap.add(10);
        intMap.toMap(hashMap);
        System.out.println(hashMap);
    }

    // Tests with collisions

    @Test
    public void testAddiGetCountString() {
        stringMap.add("BBaA");
        stringMap.add("AaBB");
        stringMap.add("BaAB");
        stringMap.add("BaAB");
        stringMap.add("BBAa");
        Assertions.assertEquals(2, stringMap.getCount("BaAB"));
        Assertions.assertEquals(1, stringMap.getCount("BBaA"));
        Assertions.assertEquals(1, stringMap.getCount("AaBB"));
        Assertions.assertEquals(1, stringMap.getCount("BBAa"));
    }

    @Test
    void testMapSize() {
        stringMap.add("BBaA");
        stringMap.add("AaBB");
        stringMap.add("BaAB");
        stringMap.add("BaAB");
        stringMap.add("BBAa");
        Assertions.assertEquals(4, stringMap.size());
    }

    @Test
    void testRemoveString() {
        stringMap.add("dawd");
        stringMap.add("awdav");
        Assertions.assertEquals(1, stringMap.remove("dawd"));
        stringMap.add("BBaA");
        stringMap.add("AaBB");
        stringMap.add("BaAB");
        stringMap.add("BaAB");
        stringMap.add("BBAa");
        Assertions.assertEquals(2, stringMap.remove("BaAB"));
        Assertions.assertEquals(4, stringMap.size());
    }

    @Test
    void testAddAllString() {
        CountMap<String> stringCountMap = new CountMapImpl<>();
        stringCountMap.add("set");
        stringCountMap.add("Extra");
        stringCountMap.add("zxxtra");
        stringCountMap.add("Dert");
        stringMap.add("set");
        stringMap.add("Dert");
        stringMap.addAll(stringCountMap);
        Assertions.assertEquals(4, stringMap.size());
        Assertions.assertEquals(2, stringMap.getCount("set"));
        Assertions.assertEquals(2, stringMap.getCount("Dert"));
    }

    @Test
    void testToMapString() {
        stringMap.add("BBaA");
        stringMap.add("AaBB");
        stringMap.add("BaAB");
        stringMap.add("BaAB");
        stringMap.add("BBAa");
        System.out.println(stringMap.toMap());
    }

    @Test
    void testToMapStringDest() {
        Map<String, Integer> hashMap = new HashMap<>();
        stringMap.add("BBaA");
        stringMap.add("AaBB");
        stringMap.add("BaAB");
        stringMap.add("BaAB");
        stringMap.add("BBAa");
        stringMap.toMap(hashMap);
        System.out.println(hashMap);
    }

    @Test
    void testResize() {
        stringMap.add("zxcwa");
        stringMap.add("wwdas");
        stringMap.add("fdfzxc");
        stringMap.add("zcxbqp");
        stringMap.add("xzc,cmw");
        stringMap.add("dwa[xcpw");
        stringMap.add("dsdawxzb");
        stringMap.add("mvncmlpw");
        stringMap.add("poilkxcd");
        stringMap.add("mcvnzxmsg");
        stringMap.add("pdwaxvnx");
        stringMap.add("plzxkcsd");
    }
}
