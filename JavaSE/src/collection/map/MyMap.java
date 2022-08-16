package collection.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author Joshua.H.Brooks
 * @description Map 遍历的三种方式：
 * 1. for循环: 增强for循环里: Map.Entry<String, String> entry : map.entrySet()
 * 2. 迭代器: map.entrySet().iterator(); 然后next.getKey(), next.getValue();
 * 3. keySet: 拿到keySet, 然后foreach里get(K)得到对应的V
 * @date 2022-08-15 20:16
 */
public class MyMap {
    static Map<String, String> map = new HashMap<String, String>();

    static {
        map.put("username", "刘德华");
        map.put("passWord", "12345");
        map.put("nickName", "华 仔");
        map.put("title", "金像奖最佳男主角");
    }

    public static void main(String[] args) {
        traverseMap();
    }

    private static void traverseMap() {
        forLoop_traverse();
        iterator_traverse();
        keySet_traverse();
    }

    private static void keySet_traverse() {
        Set<String> keyset = map.keySet();
        System.out.println("=====    keySet遍历, (K,V) 对:    =====");
        for (String key : keyset) {
            System.out.println("(" + key + ", " + map.get(key) + ")");
        }
    }

    private static void iterator_traverse() {
        Set<Map.Entry<String, String>> entries = map.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        System.out.println("=====   迭代器遍历, (K,V) 对:     =====");
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            System.out.println("(" + next.getKey() + ", " + next.getValue() + ")");
        }
    }

    private static void forLoop_traverse() {
        System.out.println("=====   for循环遍历, (K,V) 对:   =====");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("(" + entry.getKey() + ", " + entry.getValue() + ")");
        }
    }
}
