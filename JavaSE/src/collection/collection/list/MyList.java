package collection.collection.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Joshua.H.Brooks
 * @description 演示的是List独有的方法, 以ArrayList为例。
 * list特点：
 * 1. 取出顺序和放入顺序一致
 * 2. 元素可重复
 * 3. list中每个元素都有其对应顺序的索引， 可以按照索引取出
 *
 * @date 2022-08-15 17:46
 */
public class MyList {
    static ArrayList<String> heroList = new ArrayList<>();
    static{
        heroList.add("孙悟空");
        heroList.add("孙悟空");
        heroList.add("猪八戒");
        heroList.add("唐三藏");
        heroList.add("沙悟净");
        heroList.add("白龙马");
    }

    public static void main(String[] args) {
        /**
         * 遍历list, 共四种方式
         * 1. for
         * 2. foreach
         * 3. 迭代器
         * 4. JDK8 Stream API
         */
        traverse();
        System.out.println("=====================================================================");

        System.out.println("heroList.get(1) = "+heroList.get(1));
        // heroList.set(6,"刘德华"); java.lang.IndexOutOfBoundsException
        //System.out.println("heroList = " + heroList);
        heroList.set(0,"梅里尔斯特离谱"); //相当于替换， 索引不能超过边界
        System.out.println("heroList = " + heroList);
        List<String> subList = heroList.subList(0, 2);
        System.out.println("subList (结论: 前闭后开)= " + subList);


    }

    private static void traverse() {
        forLoop_traverse();
        enhancedForLoop_traverse();
        iterator_traverse();
        JDK8_streamAPI_traverse();
    }

    private static void JDK8_streamAPI_traverse() {
        System.out.println("4. JDK8 StreamAPI遍历list:");
        heroList.stream().forEach(System.out::println);
    }

    private static void iterator_traverse() {
        System.out.println("3. 迭代器遍历list:");
        Iterator<String> iterator = heroList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }

    private static void enhancedForLoop_traverse() {
        System.out.println("2. 增强for循环遍历list:");
        for (String ele:heroList) {
            System.out.println(ele);
        }
    }

    private static void forLoop_traverse() {
        System.out.println("1. for循环遍历list:");
        for (int i = 0; i < heroList.size(); i++) {
            System.out.println(heroList.get(i));
        }

    }
}
