package common;

import entity.User;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

import static utils.HbaseConnect.initHbase;


public class Hbasedata {

    //创建表


    //插入数据
    public static void insertData(String tableName, User user) throws IOException {

        TableName tablename = TableName.valueOf(tableName);
        Put put = new Put(("user-" + user.getId()).getBytes());
        //参数： 列族 列名 值
        put.addColumn("fm1".getBytes(), "name".getBytes(), user.getUsername().getBytes());
        put.addColumn("fm1".getBytes(), "age".getBytes(), user.getAge().getBytes());
        //插入到表中
        Table table = initHbase().getTable(TableName.valueOf(tableName));
        table.put(put);

    }

    //获取原始数据
    public static void getNoDealData(String tableName) throws IOException {
        try{
            Table table = initHbase().getTable(TableName.valueOf(tableName));
            Scan scan = new Scan();
            ResultScanner resultScanner = table.getScanner(scan);
            for(Result result:resultScanner){
                System.out.println("scan:" + result);
            }
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    //查询指定表名所有数据

    //根据row 进行查询
    public static User getDataByRowKey(String tableName, String rowKey) throws IOException {

       Table table = initHbase().getTable(TableName.valueOf(tableName));
       Get get = new Get(rowKey.getBytes());
       User user = new User();
       //判断是否有此条数据========有待优化，不存在 打印提示标签
        if(!get.isCheckExistenceOnly()){
            user.setId(rowKey);
            Result result = table.get(get);
            for(Cell cell : result.rawCells()){
//                String colName = Bytes.toString(cell.getQualifierArray(),cell.getRowOffset(),cell.getRowLength());
//                System.out.println(colName);   //输出 1903
//                String value = Bytes.toString(cell.getValueArray(),cell.getValueOffset(),cell.getValueLength());
//                System.out.println(value);  // 可以输出值

                String qualifier = Bytes.toString(CellUtil.cloneQualifier(cell));
                String v = Bytes.toString(CellUtil.cloneValue(cell));
                if(qualifier.equals("username")){
                    user.setUsername(v);
                }
                if(qualifier.equals("age")){
                    user.setAge(v);
                }
            }
        }else{
            System.out.println("------无此条数据-------");
        }


        return user;
    }


    //查询指定 单cell内容

}
