package test.datastructure;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class TreemapTest {
    public static void main(String[] args) {

        TreeMap<Integer, Integer> objectObjectTreeMap = new TreeMap<Integer, Integer>();

        // 自动按key自然排序
        objectObjectTreeMap.put(5, 51);
        objectObjectTreeMap.put(1, 11);
        objectObjectTreeMap.put(2, 21);
        objectObjectTreeMap.put(4, 41);
        objectObjectTreeMap.put(3, 31);

        Set<Map.Entry<Integer, Integer>> entries = objectObjectTreeMap.entrySet();
        Iterator<Map.Entry<Integer, Integer>> iterator = entries.iterator();
        while (iterator.hasNext()) {

            Map.Entry<Integer, Integer> next = iterator.next();
            System.out.println(next);
        }



        for (int key: objectObjectTreeMap.keySet()) {
            System.out.println(objectObjectTreeMap.get(key));
            
        }

    }
}
