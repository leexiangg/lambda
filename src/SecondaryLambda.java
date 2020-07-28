import java.util.*;
import java.util.stream.Collectors;

public class SecondaryLambda {

    public static List<User> users = new ArrayList<>();

    // 生成10个用户
    static {
        for (int i = 0; i < 10; i++) {
            users.add(new User(Common.randomName(), Integer.parseInt(Common.random(2))));
        }
    }

    /**
     * 获取流
     */
    public static void getStream() {
        // 顺序流
        System.out.println("获取流：顺序流：");
        users.stream().forEach(System.out::println);

        // 并行流
        System.out.println("\n获取流：并行流：");
        users.parallelStream().forEach(System.out::println);
    }

    /**
     * 操作
     */
    public static void operate() {
        // 列出所有人
        System.out.println("列出所有人：");
        System.out.println(users.stream().map(User::getName).collect(Collectors.joining(",")));

        // 按分数倒序
        System.out.println("\n按分数倒序：");
        users.stream().sorted((t, s) -> s.getScore().compareTo(t.getScore())).forEach(System.out::println);

        // 过滤出大于等于60分的人
        System.out.println("\n过滤出大于等于60分的人：");
        users.stream().filter(u -> u.getScore() >= 60).forEach(System.out::println);

        // 最高分
        User max = users.stream().max(Comparator.comparing(User::getScore)).get();
        System.out.println("\n最高分：" + max);

        // 最低分
        User min = users.parallelStream().reduce((a, b) -> a.getScore() < b.getScore() ? a : b).get();
        System.out.println("\n最低分：" + min);

        // 求分数的总值
        int sum = users.stream().mapToInt(User::getScore).sum();
        System.out.println("\n求分数的总值：" + sum);

        // 求分数的平均值
        double average = users.stream().mapToInt(User::getScore).average().getAsDouble();
        System.out.println("\n求分数的均值：" + average);
    }

    public static void main(String[] args) {
        getStream();
        System.out.println();
        operate();
    }

}
