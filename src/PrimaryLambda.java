import java.sql.SQLOutput;
import java.util.*;
import java.util.function.*;

/**
 * Lambda初级，看懂Lambda
 * @author ziyu
 */
public class PrimaryLambda {

    /**
     * 匿名内部类示例
     */
    public static void threadDemo() {
        // 非lambda形式
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("匿名内部类示例：非lambda形式");
            }
        }).run();

        // lambda形式
        new Thread(() -> System.out.println("匿名内部类示例：lambda形式")).run();
    }

    /**
     * 数组遍历
     */
    public static void arrayDemo() {
        String[] arr = new String[]{"1", "2", "3", "4"};

        // for的方式
        System.out.println("数组遍历：for的方式");
        for(int i = 0; i < arr.length; i ++) {
            System.out.println(arr[i]);
        }

        // foreach的方式
        System.out.println("数组遍历：foreach的方式");
        for(String i : arr) {
            System.out.println(i);
        }

        // lambda的方式
        System.out.println("数组遍历：lambda的方式");
        Arrays.asList(arr).forEach(i -> System.out.println(i));

        // lambda简化的方式
        System.out.println("数组遍历：lambda简化的方式");
        Arrays.asList(arr).forEach(System.out::println);
    }

    /**
     * 列表遍历
     */
    public static void listDemo() {
        Collection<String> list = Arrays.asList("1", "3", "2", "4");

        // for的方式
        System.out.println("列表遍历：for的方式");
        for(int i = 0; i < list.size(); i ++) {
            System.out.println(((List)list).get(i));
        }

        // foreach的方式
        System.out.println("列表遍历：foreach的方式");
        for(String i : list) {
            System.out.println(i);
        }

        // lambda的方式
        System.out.println("列表遍历：lambda的方式");
        list.forEach(i -> System.out.println(i));

        // lambda简化的方式
        System.out.println("列表遍历：lambda简化的方式");
        list.forEach(System.out::println);
    }

    /**
     * Map遍历
     */
    public static void mapDemo() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "A");
        map.put("3", "C");
        map.put("2", "B");

        // for先遍历key，通过key获取value
        System.out.println("Map遍历：for先遍历key，通过key获取value");
        Object[] keyArr = map.keySet().toArray();
        for (int i = 0; i < keyArr.length; i++) {
            System.out.println(keyArr[i] + " : " + map.get(keyArr[i]));
        }

        // foreach的方式
        System.out.println("Map遍历：foreach的方式");
        for(Map.Entry<String, String> entity : map.entrySet()) {
            System.out.println(entity.getKey() + " : " + entity.getValue());
        }

        // lambda遍历key，通过key获取value
        System.out.println("Map遍历：lambda遍历key，通过key获取value");
        map.keySet().forEach(key -> System.out.println(key + " : " + map.get(key)));

        // lambda的方式
        System.out.println("Map遍历：lambda的方式");
        map.forEach((key, value) -> System.out.println(key + " : " + value));
    }

    /**
     * 函数式接口
     * @see java.util.function
     */
    public static void functionDemo() {
        // Function 接收一个参数，返回一个参数
        Function f = t -> t + "Function";
        System.out.println(f.apply("函数式接口："));

        // Predicate 接收一个参数，返回boolean
        System.out.println("函数式接口：Predicate");
        Predicate p = t -> t == null;
        System.out.println(p.test(""));

        // Supplier 不接收参数，返回一个参数
        Supplier s = () -> "函数式接口：Supplier";
        System.out.println(s.get());

        // Consumer 接收一个参数，不返回
        Consumer c = t -> System.out.println(t);
        c.accept("函数式接口：Consumer");

        // BiFunction 接收两个参数，返回一个参数
        System.out.println("函数式接口：BiFunction");
        BiFunction b = (t, u) -> t == u;
        System.out.println(b.apply("2", new String("2")));
    }

    public static void main(String[] args) {
        threadDemo();
        System.out.println();
        arrayDemo();
        System.out.println();
        listDemo();
        System.out.println();
        mapDemo();
        System.out.println();
        functionDemo();
    }

}
