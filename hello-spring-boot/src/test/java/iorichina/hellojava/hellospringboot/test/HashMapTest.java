package iorichina.hellojava.hellospringboot.test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by iorichina on 2017/1/19.
 */
public class HashMapTest {
    public static void main(String[] args) throws Exception {
        slowHashMapWithSameKeyHashCode();
    }

    /**
     * 测试：HashMap操作hashcode设计不合理的key；<p></p>
     * 证明：如果HashMap key的hashcode设计不合理，会导致HashMap（读？）写都非常慢；<br>
     * 同时HashMap里的table会极大的浪费内存空间(see: ArrayTest::testEmptyArrayMemory)；<p></p>
     * 说明：使用一个自定义对象做key时要思考重写hashcode，且设计是否合理;<p></p>
     * 栗子：
     *
     * @throws Exception
     */
    public static void slowHashMapWithSameKeyHashCode() throws Exception {
        HashMap<Key, Integer> hashMap = new HashMap<>();
        Field field = HashMap.class.getDeclaredField("table");
        field.setAccessible(true);
        int length = 0;
        int tableLength;
        for (int i = 0; i < 1_000_000; i++) {
            Key one = new Key(i);
            hashMap.put(one, i);

            @SuppressWarnings("unchecked")
            Map.Entry<Key, Integer>[] entries = (Map.Entry<Key, Integer>[]) field.get(hashMap);
            tableLength = entries.length;
            if (tableLength > length) {
                System.out.println("element size=" + hashMap.size() + "; table length=" + tableLength);
                length = tableLength;
            }
        }
    }

    public static class Key {
        public int index;

        public Key() {
        }

        public Key(int index) {
            this.index = index;
        }

        @Override
        public int hashCode() {
            return 3;
        }
    }
}
