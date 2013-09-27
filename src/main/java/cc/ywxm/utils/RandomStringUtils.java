package cc.ywxm.utils;

import java.util.Random;

public class RandomStringUtils {

    // 产生一个随机数 方法1
    public static String getRandomString(int length) {
        String str = "abcdefghigklmnopkrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sf = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);// 0~61
            sf.append(str.charAt(number));

        }
        return sf.toString();
    }

    // 产生一个随机数 方法2
    public static String getRandomString2(int length) {
        Random random = new Random();
        StringBuffer sf = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(3);
            long result = 0;

            switch (number) {
                case 0:
                    result = Math.round(Math.random() * 25 + 65);
                    sf.append(String.valueOf((char) result));
                    break;

                case 1:
                    result = Math.round(Math.random() * 25 + 97);
                    sf.append(String.valueOf((char) result));
                    break;
                case 2:
                    sf.append(String.valueOf(new Random().nextInt(10)));
                    break;

            }
        }

        return sf.toString();
    }

    public static void main(String args[]) {

        System.out.println(getRandomString(16));
        System.out.println(getRandomString2(16));
    }
}