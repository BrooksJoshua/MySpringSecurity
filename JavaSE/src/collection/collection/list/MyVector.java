package collection.collection.list;

import java.util.Vector;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-08-16 16:16
 */
public class MyVector {
    public static void main(String[] args) {
        Vector vector = new Vector<>();
        for (int i = 0; i < 10; i++) {
            vector.add(i);
        }
        vector.add(11);
    }
}
