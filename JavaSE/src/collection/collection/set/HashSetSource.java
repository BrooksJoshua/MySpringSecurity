package collection.collection.set;
import java.util.HashSet;
/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-08-16 22:52
 */
public class HashSetSource {
    public static void main(String[] args) {
        HashSet<String> set = new HashSet<>(); // 底层new 了一个HashMap并设置加载因子为默认的加载因子0.75
        set.add("Java");
        set.add("C");
        set.add("C++");
        set.add("Java");
        set.add("java");
        set.add("MySQL");
        HashSet set2 = new HashSet<>();
        for (int i = 0; i < 11; i++) {
            set2.add(i);
        }
        for (int i = 11; i < 16; i++) {
            set2.add(i);
        }
        for (int i = 16; i < 32; i++) {
            set2.add(i);
        }
    }

}
