package collection.collection.list;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Joshua.H.Brooks
 * @description ArrayList 源码分析
 * 1. 空参构造的ArrayList初始化容量capacity为0， 第一个元素被添加到当前list后， 其容量变为10， 之后需要扩容时会每次扩容1.5倍capacity。
 * 2. 有参构造的ArrayList初始化容量为指定的容量， 每次扩容也是1.5倍。
 * 3. ArrayList底层是用数组实现的,
 * 4. Integer.MAX_VALUE - 8
 * @date 2022-08-15 21:57
 */
public class ListSource {


    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        //sourceFeature1_2();
        demo_sourceFeature1_2();
        //sourceFeature3();


    }

    private static void demo_sourceFeature1_2() {
        ArrayList list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        for (int i = 11; i < 15; i++) {
            list.add(i);
        }
        list.add(100);
        list.add(null);

    }

    private static void sourceFeature3() {


    }

    private static void sourceFeature1_2() throws NoSuchFieldException, IllegalAccessException {
        ArrayList<String> list = new ArrayList<>();
        System.out.println(list.size());
        Class<ArrayList> arrayListClass = ArrayList.class;
        Field capacity = arrayListClass.getDeclaredField("elementData");
        capacity.setAccessible(true);
        Object[] val = (Object[]) capacity.get(list);
        System.out.println(Arrays.asList(val));
        //向list中添加一个元素后再看其capacity
        list.add("1");
        val = (Object[]) capacity.get(list);
        System.out.println(Arrays.asList(val));

        ArrayList<String> list2 = new ArrayList<>(5);
        val = (Object[]) capacity.get(list2);
        System.out.println(Arrays.asList(val));
    }
}
