package common;

import org.apache.commons.math3.geometry.spherical.oned.S1Point;
import org.apache.hadoop.hbase.util.Bytes;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class javaTest {
    public static void test1() throws NoSuchAlgorithmException {
        Long l = 123456789067937L;
        byte[] bytes = Bytes.toBytes(l);
        System.out.println("bytes length:" + bytes.length);

        String s = "123456789067348670867503749307423094729308743749";
        byte[] bytes1 = Bytes.toBytes(s);
        System.out.println("bytes1:" + bytes1.length);


        /*
        * MD (Message Digest)  消息摘要算法  MD2/MD5
        *SHA(Secure Hash Algorithm) 安全散列算法
        *MAC(Message Authentication Code) 消息认证码算法
        * */



        //hash
        System.out.println("****************hash SHA1***************");
        MessageDigest ms = MessageDigest.getInstance("SHA1");
        byte[] digest = ms.digest(Bytes.toBytes(s));
        String s1 = new BigInteger(1,digest ).toString(16);
        System.out.println(s1 + " ,s1.length:" + s1.length() + " ,digest.length:" + digest.length);

        System.out.println("****************hash MD5***************");
        MessageDigest ms2 = MessageDigest.getInstance("MD5");
        byte[] digest2 = ms2.digest(Bytes.toBytes(s));  //Bytes.toBytes(s) 可以写成 s.getBytes()
        String s2 = new BigInteger(1,digest2 ).toString(16);
        System.out.println(s2 + " ,s2.length:" +s2.length() +  " ,digest2.length:" + digest2.length);



        System.out.println("****************hash 2***************");
        //初始化
        MessageDigest m = MessageDigest.getInstance("MD5");
        //更新
        m.update(s.getBytes(),0,s.length());
        // m.digest() 生成摘要
        String md5Val = new BigInteger(1,m.digest()).toString(16);
        System.out.println(md5Val + " ,md5length:" + md5Val.length());


    }
}
