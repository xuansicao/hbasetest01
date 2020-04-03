package APP;

import common.javaTest;
import entity.Dep;

import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static common.Depdata.*;
import static common.javaTest.*;
import static utils.HbaseConnect.initHbase;

public class HbaseTest {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        //单例值过滤器，返回满足条件的整行，包含 "org_name"列
        getByCloumnfilter("ods:dep_test","info","org_name","四川");

        //单例值过滤器，返回满足条件的整行，但是不包含需过滤的列。 不包含 "org_name"列
        getByCloumnExcludefilter("ods:dep_test","info","org_name","四川");

        //MD5
       // test1();



        //根据value 获取数据
       // getByValue("ods:dep_test","Z07");


//         System.out.println("----------获取原始数据----------");
//         getAll("ods:dep_test");
          //getNoDealData("ods:test01");

//        System.out.println("----------获取单条数据----------");
//        User data = getDataByRowKey("ods:dep_test", "8895047431097902486");
//        System.out.println(data.toString());
        //  System.out.println("----------插入数据----------");
        //  User us = new User("005","小小","30");
        //insertData("ods:test01",us);



       // System.out.println("----------获取单条数据----------");
//        Dep data = getData("ods:dep_test", "9081841839081869511");
//        System.out.println(data.toString());

        //插入数据
        // insertdata("ods:dep_test", "001", "info", "org_name", "张三");


//          System.out.println("----------插入数据----------");
//          String str = hashFunc("5061233139039242992");
//        System.out.println(str);
//          Dep de = new Dep(str,"西宁特通批发经销所","H32");
//        insertDep("ods:dep_test",de);




//        Admin admin = initHbase().getAdmin();
//        Connection con = initHbase();
//        try{
//            if(admin != null){
//                admin.close();
//            }
//            if(null != con){
//                con.close();
//            }
//        }catch(IOException e){
//            e.printStackTrace();
//        }


    }
}
