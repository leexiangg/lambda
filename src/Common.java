import java.util.Arrays;
import java.util.Random;

public class Common {

    /** 中国前一百姓氏排名 */
    private static final String[] firstNames = new String[]{"李", "王", "张", "刘", "陈", "杨", "赵", "黄", "周", "吴", "徐", "孙", "胡", "朱", "高", "林", "何", "郭", "马", "罗", "梁", "宋", "郑", "谢", "韩", "唐", "冯", "于", "董", "萧", "程", "曹", "袁", "邓", "许", "傅", "沈", "曾", "彭", "吕", "苏", "卢", "蒋", "蔡", "魏", "贾", "丁", "薛", "叶", "阎", "余", "潘", "杜", "戴", "夏", "钟", "汪", "田", "任", "姜", "范", "方", "石", "姚", "谭", "廖", "邹", "熊", "金", "陆", "郝", "孔", "白", "崔", "康", "毛", "邱", "秦", "江", "史", "顾", "侯", "邵", "孟", "龙", "万", "段", "雷", "钱", "汤", "尹", "易", "黎", "常", "武", "乔", "贺", "赖", "龚", "文"};

    /** 中国前一百姓氏万分比 */
    private static final int[] firstNamesPercent = new int[]{794, 741, 707, 538, 453, 308, 229, 223, 212, 205, 173, 152, 131, 126, 121, 118, 117, 115, 105, 86, 84, 81, 78, 72, 68, 65, 64, 62, 61, 59, 57, 57, 54, 54, 54, 51, 50, 50, 49, 47, 47, 47, 47, 46, 45, 42, 42, 42, 42, 41, 41, 41, 40, 39, 39, 38, 37, 37, 37, 36, 36, 36, 35, 35, 34, 34, 33, 32, 32, 31, 30, 29, 29, 28, 28, 27, 27, 26, 26, 25, 25, 25, 24, 24, 24, 24, 23, 23, 22, 19, 19, 19, 19, 18, 18, 18, 18, 18, 17, 17};

    /**
     * 生成随机n个汉字
     */
    public static String randomChinese(int n) {
        StringBuilder str = new StringBuilder();
        try {
            while (n-- > 0) {
                int hightPos, lowPos; // 定义高低位
                Random random = new Random();
                hightPos = 176 + random.nextInt(39);//获取高位值
                lowPos = 161 + random.nextInt(93);//获取低位值
                byte[] b = new byte[2];
                b[0] = new Integer(hightPos).byteValue();
                b[1] = new Integer(lowPos).byteValue();
                str.append(new String(b, "GBk"));//转成中文
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str.toString();
    }

    /**
     * 随机生成中文名
     */
    public static String randomName() {
        Random random = new Random();
        // 挑选姓
        int sumPercent = Arrays.stream(firstNamesPercent).sum();
        int randomPercent = random.nextInt(sumPercent);
        // TODO 把下面逻辑改为lambda
        int sum = 0, index = 0;
        for (int i = 0; i < firstNamesPercent.length; i++) {
            sum += firstNamesPercent[i];
            if(sum > randomPercent) {
                index = i;
                break;
            }
        }
        String firstName = firstNames[index];

        // 随机一字或者两字名，一字名与两子名的比例按3：7计算
        String secondName = "";
        if(random.nextInt(10) < 7) {
            secondName = randomChinese(1);
        }
        secondName += randomChinese(1);

        return firstName + secondName;
    }

    /**
     * 生成n位随机数
     * 首位不为零
     */
    public static String random(int n) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        sb.append(random.nextInt(9) + 1);
        while(n-- > 1) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("随机五位数字：" + random(5) + "\n");
        System.out.println("随机姓名：");
        for (int i = 0; i < 100; i++) {
            System.out.println(randomName());
        }
    }

}
