package collection.collection.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author Joshua.H.Brooks
 * @description 演示LinkedList的一些特性
 * 1. 底层实现的数据结构是双向链表， 可以从源码中维护的内部类Node看出。
 * 2. 由于是双向链表实现， 所以添加和删除元素效率很高。
 * 3.
 * @date 2022-08-16 16:39
 */
public class MyLinkedList {
    static LinkedList<String> heroList = new LinkedList<>();
    static{
        heroList.add("武大郎");
        heroList.add("潘金莲");
        heroList.add("孙悟空");
        heroList.add("猪八戒");
        heroList.add("唐三藏");
        heroList.add("孙悟空");
        heroList.add("沙悟净");
        heroList.add("孙悟空");
    }

    public static void main(String[] args) {
        /**
         * 演示LinkedListde各种remove方法
         */
       // removeDemos();
        /**
         * 遍历
         */
        traverse();

    }

    private static void traverse() {
        forLoop_traverse();
        enchantedForLoop_traverse();
        iteration_traverse();
        JDK8_streamAPI_traverse();

    }

    private static void JDK8_streamAPI_traverse() {
        System.out.println("4. JDK8 Stream API遍历");
        heroList.forEach(System.out::println);
    }

    private static void forLoop_traverse() {
        System.out.println("1. for循环遍历");
        for (int i = 0; i < heroList.size(); i++) {
            System.out.println(heroList.get(i));
        }
    }

    private static void enchantedForLoop_traverse() {
        System.out.println("2. 增强for遍历");
        for (String ele:heroList) {
            System.out.println(ele);
        }
    }

    private static void iteration_traverse() {
        Iterator<String> iterator = heroList.iterator();
        System.out.println("3. 迭代器遍历");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private static void removeDemos() {
        heroList.add("齐天大圣孙悟空"); //追进源码可以看见其实添加就是将新元素link到last上
        System.out.println("heroList = " + heroList);
        LinkedList<String> heroList2 = new LinkedList<>();
        LinkedList<String> heroList3 = new LinkedList<>();
        LinkedList<String> heroList4 = new LinkedList<>();
        LinkedList<String> heroList5 = new LinkedList<>();
        heroList2.addAll(heroList);
        heroList3.addAll(heroList);
        heroList4.addAll(heroList);
        heroList5.addAll(heroList);

        String removedElement = heroList2.remove();//默认删除的是第一个
        System.out.println("remove: heroList2 = " + heroList2);
        String removedFirstElement = heroList3.removeFirst();
        System.out.println("removeFirst: heroList3 = " + heroList3);
        boolean removeFirstOccurrenceFlag = heroList4.removeFirstOccurrence("孙悟空");
        System.out.println("removeFirstOccurrence: heroList4 = " + heroList4);
        boolean removeLastOccurrenceFlag = heroList5.removeLastOccurrence("孙悟空");
        System.out.println("removeLastOccurrence: heroList5 = " + heroList5);
        System.out.println(removedElement+" "+removedFirstElement+" "+removeFirstOccurrenceFlag+" "+removeLastOccurrenceFlag);

    }

}
