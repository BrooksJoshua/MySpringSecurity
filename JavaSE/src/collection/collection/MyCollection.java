package collection.collection;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Joshua.H.Brooks
 * @description 演示Collection的最常见方法, 是实现了Collection接口的类都有的方法
 * add, add, addAll, addAll
 * remove, remove, removeIf, removeAll
 * size, clear, isEmpty, indexOf, lastIndexOf
 * @date 2022-08-15 14:57
 */
public class MyCollection {
    static ArrayList<String> list0 = new ArrayList<>();
    static ArrayList<String> list1 = new ArrayList<>();
    static{
        list0.add("三国");
        list0.add("水浒");
        list0.add("西游");
        list0.add("红楼");
        list0.add("三国");
    }
    static{
        list1.add("水浒");
        list1.add("红楼");
        list1.add("她比烟花寂寞");
    }
    public static void main(String[] args) {
        //addDemo();
        //frequentMethods();
        // removeDemo();
        iteration();
    }

    private static void iteration() {
        System.out.println("迭代器方式");
        Iterator<String> iterator = list0.iterator();
        while (iterator.hasNext()) {
            System.out.println("iterato.next() = " + iterator.next());

        }
        //退出循环后， 游标指向最后一个元素， 如果再调用 .next()， 则会异常
        //如果需要再次遍历， 需要重置迭代器， 让游标再次指向第一个元素之前的位置
        iterator = list0.iterator();
        System.out.println("重置迭代器后   iterator.next() = " + iterator.next()); // java.util.NoSuchElementException
        System.out.println("foreach(也叫增强for,底层还是迭代器 )方式遍历:");
        for (String ele:list0) {
            System.out.println(ele);
        }

        System.out.println("JDK8 Stream流 API");
        list0.stream().forEachOrdered(System.out::println);

    }


    private static void removeDemo() {
        System.out.println("=====   删 remove(index), remove(Object), removeIf(Predicate), removeAll()    =====");
        System.out.println("list0.remove(0) = " + list0.remove(0) + ";\t注意remove(index)的返回是要删除的元素, remove(obj)的返回是boolean是否删除成功, 删除存在的元素返回true,不存在的元素, 返回false");
        System.out.println("list0 = " + list0);
        System.out.println("list0.remove(\"西游\") = " + list0.remove("西游"));
        System.out.println("list0 = " + list0);
        System.out.println("list0.remove(\"金瓶梅\") = " + list0.remove("金瓶梅"));
        System.out.println("list0 = " + list0);
        System.out.println("list0.removeAll(list1) = " + list0.removeAll(list1));
        System.out.println("list0 = " + list0);
        System.out.println("list0.removeAll(list1) = " + list0.removeAll(list1));
        System.out.println("list0 = " + list0);
        System.out.println("list0.removeIf(e->e.equals(\"三国\")) = " + list0.removeIf(e -> e.equals("三国")));
        System.out.println("list0 = " + list0);
    }

    private static void frequentMethods() {
        System.out.println("======    constains, containsAll, size, clear");
        System.out.println("list0.contains(\"三国\") = " + list0.contains("三国"));
        System.out.println("list0.containsAll(list1) = " + list0.containsAll(list1));
        System.out.println("list0.size() = " + list0.size());
        System.out.println("list0.isEmpty() = " + list0.isEmpty());
        System.out.println("list0.indexOf(\"三国\") = " + list0.indexOf("三国"));
        System.out.println("list0.lastIndexOf(\"三国\") = " + list0.lastIndexOf("三国"));
        list0.clear();
        System.out.println("list0.clear()后的结果 = " + list0);
    }

    private static void addDemo() {
        ArrayList<String> list0 = new ArrayList<>();
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        System.out.println("=====   增add(val),add(index, val), addAll(collection), addAll(index,collection)   =====");
        list0.add("三国");
        list0.add("西游");
        list1.add("红楼");
        list1.add("水浒");
        list2.add("三国志");
        list2.add("三国演义");
        System.out.println("list0 = " + list0);
        System.out.println("list1 = " + list1);
        System.out.println("list2 = " + list2);
        list0.addAll(list1);
        System.out.println("list0 = " + list0);
        list0.addAll(1,list2);
        System.out.println("list0 = " + list0);
    }

}
