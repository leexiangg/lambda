import java.util.function.*;

public class HigherLambda {

    /**
     * Function 接收一个参数，返回一个参数
     */
    public static void functionDemo() {
        Function<Integer, Integer> f1 = t -> t * t;
        Function<Integer, Integer> f2 = t -> t * 2;

        // apply 执行Function，返回执行结果
        System.out.println("Function：apply");
        System.out.println(f1.apply(3));

        // compose 在 f1 之前执行 f2 : (3 * 2) * (3 * 2) = 36
        System.out.println("Function：compose");
        System.out.println(f1.compose(f2).apply(3));

        // andThen 在 f1 之后执行 f2 : 3 * 3 * 2 = 18
        System.out.println("Function：andThen");
        System.out.println(f1.andThen(f2).apply(3));

        // identity 返回本身
        System.out.println(Function.identity().apply("Function：identity"));

        // compose 和 andThen 一起使用
        System.out.println("Function：compose & andThen");
        // compose(f2) & f1.apply() & andThen(f2) : [(3 * 2) * (3 * 2)] * 2 = 72
        System.out.println(f1.compose(f2).andThen(f2).apply(3));
        // compose(f1) & compose(f2) & f1.apply() & andThen(f2) : {[(5 * 5) * 2] * [(5 * 5) * 2]} * 2 = 5000
        System.out.println(f1.compose(f2).andThen(f2).compose(f1).apply(5));
        // compose(f2) & compose(f1) & f1.apply() & andThen(f2) : {[(5 * 2) * (5 * 2)] * [(5 * 2) * (5 * 2)]} * 2 = 20000
        System.out.println(f1.compose(f1).andThen(f2).compose(f2).apply(5));
        // compose(f2) & f1.apply() & andThen(f1) & andThen(f2) : {[(5 * 2) * (5 * 2)] * 2} * {[(5 * 2) * (5 * 2)] * 2} = 40000
        System.out.println(f1.compose(f2).andThen(f2).andThen(f1).apply(5));
    }

    /**
     * Predicate 接收一个参数，返回boolean
     */
    public static void predicateDemo() {
        Predicate<Integer> p1 = t -> t % 4 == 0;
        Predicate<Integer> p2 = t -> t % 100 != 0;

        // test 执行Predicate，返回执行结果
        System.out.println("Predicate：test");
        System.out.println(p1.test(2020));

        // and p1 && p2
        System.out.println("Predicate：and");
        System.out.println(p1.and(p2).test(2020));

        // negate 否
        System.out.println("Predicate：negate");
        System.out.println(p1.negate().test(2020));

        // or p1 || p2
        System.out.println("Predicate：or");
        System.out.println(p1.or(p2).test(2020));

        // isEqual
        System.out.println("Predicate：isEqual");
        Predicate<Integer> p = Predicate.isEqual(2020);
        System.out.println(p.test(2020));
    }

    /**
     * Supplier 不接收参数，返回一个参数
     */
    public static void supplierDemo() {
        Supplier<String> s1 = () -> "Supplier";

        // get 返回固定信息
        System.out.println("Supplier：get");
        System.out.println(s1.get());
    }

    /**
     * Consumer 接收一个参数，不返回
     */
    public static void consumerDemo() {
        Consumer<String> c = System.out::println;

        // accept 执行Consumer
        System.out.println("Consumer：accept");
        c.accept("accept");

        // andThen
        System.out.println("Consumer：andThen");
        c.andThen(c).accept("andThen");
    }

    /**
     * BiFunction 接收两个参数，返回一个参数
     */
    public static void biFunctionDemo() {
        BiFunction<String, String, String> b = (t, s) -> t + s;
        Function<String, String> f = t -> t + "apply";

        // accept 执行Consumer
        System.out.println(b.apply("BiFunction：", "apply"));

        // andThen b的返回值传入f的输入
        System.out.println(b.andThen(f).apply("BiFunction：", "andThen："));
    }

    /**
     * TrFunction 接收三个参数，返回一个参数
     * 自定义的函数接口
     */
    public static void trFunctionDemo() {
        TrFunction<String, String, String, String> tr = (t, u, s) -> t + u + s;
        System.out.println(tr.apply("123", "456", "789"));
    }

    /**
     * 自定义方法
     * @param obj
     * @param function
     * @return
     */
    public static Object reduce(Object obj, Function<Object, Object> function) {
        return function.apply(obj);
    }

    public static void main(String[] args) {
        functionDemo();
        System.out.println();
        predicateDemo();
        System.out.println();
        supplierDemo();
        System.out.println();
        consumerDemo();
        System.out.println();
        biFunctionDemo();
        System.out.println();
        trFunctionDemo();
        System.out.println(reduce("Hello ", t -> t + "World"));
    }

}
