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
        }).start();

        // lambda形式
        new Thread(() -> System.out.println("匿名内部类示例：lambda形式")).start();
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
        for (Object o : map.keySet().toArray()) {
            System.out.println(o + " : " + map.get(o));
        }

        // lambda遍历key，通过key获取value
        System.out.println("Map遍历：lambda遍历key，通过key获取value");
        map.keySet().forEach(key -> System.out.println(key + " : " + map.get(key)));

        // foreach的方式
        System.out.println("Map遍历：foreach的方式");
        for(Map.Entry<String, String> entity : map.entrySet()) {
            System.out.println(entity.getKey() + " : " + entity.getValue());
        }

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
        Function<String, String> f = t -> t + "Function";
        System.out.println(f.apply("函数式接口："));

        // Predicate 接收一个参数，返回boolean
        System.out.println("函数式接口：Predicate");
        Predicate<String> p = Objects::isNull;
        System.out.println(p.test(""));

        // Supplier 不接收参数，返回一个参数
        Supplier<String> s = () -> "函数式接口：Supplier";
        System.out.println(s.get());

        // Consumer 接收一个参数，不返回
        Consumer<String> c = System.out::println;
        c.accept("函数式接口：Consumer");

        // BiFunction 接收两个参数，返回一个参数
        System.out.println("函数式接口：BiFunction");
        BiFunction<Object, Object, Boolean> b = Objects::equals;
        System.out.println(b.apply("2", 2));

        // 还有比较常见的 Runnable、Comparator等
    }

    /**
     * Stream工具
     */
    public static void toolDemo() {
        Collection<String> list = Arrays.asList("1", "3", "2", "4", "2");

        // filter过滤
        System.out.println("Stream工具：filter");
        list.stream().filter(t -> !t.equals("2")).forEach(System.out::println);

        // map转换元素值
        System.out.println("Stream工具：map");
        list.stream().map(t -> t + "8").forEach(System.out::println);

        // limit保留前n个元素
        System.out.println("Stream工具：limit");
        list.stream().limit(2).forEach(System.out::println);

        // skip跳过前n个元素
        System.out.println("Stream工具：skip");
        list.stream().skip(2).forEach(System.out::println);

        // distinct剔除重复元素
        System.out.println("Stream工具：distinct");
        list.stream().distinct().forEach(System.out::println);

        // sorted默认排序
        System.out.println("Stream工具：sorted");
        list.stream().sorted().forEach(System.out::println);

        // sorted倒序
        System.out.println("Stream工具：sorted desc");
        list.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);

        // reduce计算
        System.out.println("Stream工具：reduce");
        String result = list.stream().reduce((t, s) -> String.valueOf(Integer.sum(Integer.parseInt(t), Integer.parseInt(s)))).get();
        System.out.println(result);

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
        System.out.println();
        toolDemo();
    }

}
