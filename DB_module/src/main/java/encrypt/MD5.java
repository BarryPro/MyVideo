package encrypt;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Scanner;

/**
 * Created by belong on 2016/12/2.
 */
public class MD5 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            String str = in.nextLine();
            if(MD5.getMD5(str).equals(MD5.getMD5("belong"))){
                System.out.println("true");
            } else {
                System.out.println("false");
            }
        }
    }
    /**
     * 对字符串md5加密
     *
     * @param str
     * @return
     */
    public static String getMD5(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            try {
                throw new Exception("MD5加密出现错误");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return "";
    }
}
