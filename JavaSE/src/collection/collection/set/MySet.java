package collection.collection.set;

import java.util.HashSet;
import java.util.Iterator;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-08-16 21:46
 */
public class MySet {
    static HashSet<String> heros = new HashSet<>();

    static {
        heros.add("孙悟空");
        heros.add("孙悟空");
        heros.add("猪八戒");
        heros.add("沙悟净");
        heros.add(null);
        heros.add(null);

    }

    public static void main(String[] args) {
        //features();
        //traverse();
        setInterfaceAPIs();
    }

    private static void setInterfaceAPIs() {
        System.out.println("heros = " + heros);
        boolean add = heros.add("123");
        System.out.println("heros.add(\"123\") 后 = " + heros);
        HashSet<String> heros2 = new HashSet<>();
        heros2.add("111");
        heros2.add("222");
        heros.addAll(heros2);
        System.out.println("heros.add(heros2) 后 = " + heros);
        heros.remove("孙悟空");
        System.out.println("heros.remove(\"孙悟空\") = " + heros);
        System.out.println("heros.contains(\"111\") = " + heros.contains("111"));
        System.out.println("heros.containsAll(heros2) = " + heros.containsAll(heros2));
        System.out.println("heros.isEmpty() = " + heros.isEmpty());
        System.out.println("heros.size() = " + heros.size());
        Object[] s = heros.toArray();
        System.out.println("-------------");
        for (int i = 0; i < s.length; i++) {
            System.out.println(s[i]);
        }
        String[] ss = {};
        String[] sss = heros.toArray(ss);
        System.out.println("-------------");
        for (int i = 0; i < sss.length; i++) {
            System.out.println(sss[i]);
        }
        heros.clear();
        System.out.println("heros.clear()后 =  " + heros);
    }

    /**
     * 演示各种遍历方式
     * 注意⚠️： 因为set没有类似list那样的索引，所以没有提供get(index)方法，进而不能使用for循环遍历
     */
    private static void traverse() {
        iterator_traverse();
        enchancedForLoop_traverse();
    }

    private static void enchancedForLoop_traverse() {
        System.out.println("1. 增强for循环遍历");
        for (String element : heros) {
            System.out.println(element);
        }
    }

    private static void iterator_traverse() {
        System.out.println("2. 迭代器遍历");
        Iterator<String> iterator = heros.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private static void features() {
        for (int i = 0; i < 20; i++) {
            System.out.println(heros);
        }
    }
}
