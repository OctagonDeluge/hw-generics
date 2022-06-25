import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class TestHashMap {

    @Test
    void testIndexBeforeResize() {
        String key1 = "DFHXR";
        String key2 = "YSXFJ";
        String key3 = "TUDDY";
        String key4 = "AXVUH";
        String key5 = "RUTWZ";
        String key6 = "DEDUC";
        String key7 = "WFCVW";
        String key8 = "ZETCU";
        String key9 = "GCVUR";
        System.out.println(((16 - 1) & hash(key1)));
        System.out.println(((16 - 1) & hash(key2)));
        System.out.println(((16 - 1) & hash(key3)));
        System.out.println(((16 - 1) & hash(key4)));
        System.out.println(((16 - 1) & hash(key5)));
        System.out.println(((16 - 1) & hash(key6)));
        System.out.println(((16 - 1) & hash(key7)));
        System.out.println(((16 - 1) & hash(key8)));
        System.out.println(((16 - 1) & hash(key9)));
    }

    @Test
    void testIndexAfterResize() {
        int length = 128;
        String key1 = "DFHXR";
        String key2 = "YSXFJ";
        String key3 = "TUDDY";
        String key4 = "AXVUH";
        String key5 = "RUTWZ";
        String key6 = "DEDUC";
        String key7 = "WFCVW";
        String key8 = "ZETCU";
        String key9 = "GCVUR";
        String key10 = "BBaA";
        String key11 = "AaBB";
        String key12 = "BaAB";
        String key13 = "BBAa";
        System.out.println(((length - 1) & hash(key1)));
        System.out.println(((length - 1) & hash(key2)));
        System.out.println(((length - 1) & hash(key3)));
        System.out.println(((length - 1) & hash(key4)));
        System.out.println(((length - 1) & hash(key5)));
        System.out.println(((length - 1) & hash(key6)));
        System.out.println(((length - 1) & hash(key7)));
        System.out.println(((length - 1) & hash(key8)));
        System.out.println(((length - 1) & hash(key9)));
        System.out.println(((length - 1) & hash(key10)));
        System.out.println(((length - 1) & hash(key11)));
        System.out.println(((length - 1) & hash(key12)));
        System.out.println(((length - 1) & hash(key13)));
    }


    @Test
    void testRemove() {
        Integer key = 65536;
        System.out.println(key.hashCode());
        int test;
        int h;
        h = key.hashCode();
        System.out.println(Integer.toBinaryString(h));
        System.out.println(Integer.toBinaryString(h >>> 16));
        test = h ^ (h >>> 16);
        System.out.println(Integer.toBinaryString(test));
        System.out.println(Integer.toBinaryString(15));
        System.out.println("C преобразованием " + ((16 - 1) & test));
        System.out.println("без преобразования " + ((16 - 1) & key.hashCode()));
    }

    @Test
    void testEqualsHash() {
        String key1 = "AaAaAa";
        String key2 = "AaAaBB";
        String key3 = "BBaA";
        String key4 = "AaBB";
        String key5 = "BaAB";
        String key6 = "BBAa";
        System.out.println(key1.hashCode());
        System.out.println(key3.hashCode());
        System.out.println(key4.hashCode());
        System.out.println(key5.hashCode());
        System.out.println(key6.hashCode());
        System.out.println(((16 - 1) & hash(key1)));
        System.out.println(((16 - 1) & hash(key2)));
        System.out.println(((16 - 1) & hash(key3)));
        System.out.println(((16 - 1) & hash(key4)));
        System.out.println(((16 - 1) & hash(key5)));
        System.out.println(((16 - 1) & hash(key6)));
    }

    @Test
    void testHashMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("BBaA", 1);
        map.put("Set",1);
        map.remove("BBaA");
        map.remove("Set");
    }

    int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
