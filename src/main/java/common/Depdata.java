package common;

import entity.Dep;
import entity.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.CompareOperator;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.filter.SingleColumnValueExcludeFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.filter.ValueFilter;
import org.apache.hadoop.hbase.tool.LoadIncrementalHFiles;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static utils.HbaseConnect.getConf;
import static utils.HbaseConnect.initHbase;

public class Depdata {

    //对 rowkey 加密 MD5
    public static String hashFunc(String str){
        return DigestUtils.md5Hex(str);
    }


    //根据 value 查询
    public static void getByValue(String tablename, String value) throws IOException {
        Connection connection = initHbase();
        Table table = connection.getTable(TableName.valueOf(tablename));
        ValueFilter valueFilter = new ValueFilter(CompareOperator.EQUAL,new SubstringComparator(value));  //包含
        Scan scan = new Scan();
        scan.setFilter(valueFilter);

        //通过value获取数据
        ResultScanner scanner = table.getScanner(scan);
        for(Result sc:scanner){
            for(Cell cell: sc.rawCells()){
                String rowkey = Bytes.toString(CellUtil.cloneRow(cell));
                String cf = Bytes.toString(CellUtil.cloneFamily(cell));
                String qual = Bytes.toString(CellUtil.cloneQualifier(cell));
                String val = Bytes.toString(CellUtil.cloneValue(cell));
                //输出
                System.out.println("rowkey：" + rowkey + " ,cd：" + cf + " ,qual：" + qual + " ,value：" + val);
            }
        }
        table.close();
        connection.close();
    }


    //单例值过滤器，返回满足条件的整行
    public static void getByCloumnfilter(String tablename, String cf, String qul, String value) throws IOException {
        Connection connection = initHbase();
        Table table = connection.getTable(TableName.valueOf(tablename));
        Scan scan = new Scan();
        SingleColumnValueFilter valueFilter = new SingleColumnValueFilter(
                Bytes.toBytes(cf),
                Bytes.toBytes(qul),
                CompareOperator.EQUAL,
                new SubstringComparator(value)
        );
        //设置 true ，只返回包含指定 column的行
        valueFilter.setFilterIfMissing(true);
        scan.setFilter(valueFilter);
        ResultScanner scanner = table.getScanner(scan);
        for(Result sc:scanner){
            for(Cell cell:sc.rawCells()){
                String rowkey = Bytes.toString(CellUtil.cloneRow(cell));
                String c = Bytes.toString(CellUtil.cloneFamily(cell));
                String qual = Bytes.toString(CellUtil.cloneQualifier(cell));
                String val = Bytes.toString(CellUtil.cloneValue(cell));
                //输出
                System.out.println("rowkey：" + rowkey + " ,cd：" + c + " ,qual：" + qual + " ,value：" + val);
            }
        }
        table.close();
        connection.close();
    }

    //单例值过滤器，返回满足条件的整行，但是不包含需过滤的列
    public static void getByCloumnExcludefilter(String tablename, String cf, String qul, String value) throws IOException {
        Connection connection = initHbase();
        Table table = connection.getTable(TableName.valueOf(tablename));
        Scan scan = new Scan();
        SingleColumnValueExcludeFilter valueFilter = new SingleColumnValueExcludeFilter(
                Bytes.toBytes(cf),
                Bytes.toBytes(qul),
                CompareOperator.EQUAL,
                new SubstringComparator(value)
        );
        //设置 true ，只返回包含指定 column的行
        valueFilter.setFilterIfMissing(true);
        scan.setFilter(valueFilter);
        ResultScanner scanner = table.getScanner(scan);
        for(Result sc:scanner){
            for(Cell cell:sc.rawCells()){
                String rowkey = Bytes.toString(CellUtil.cloneRow(cell));
                String c = Bytes.toString(CellUtil.cloneFamily(cell));
                String qual = Bytes.toString(CellUtil.cloneQualifier(cell));
                String val = Bytes.toString(CellUtil.cloneValue(cell));
                //输出
                System.out.println("rowkey：" + rowkey + " ,cd：" + c + " ,qual：" + qual + " ,value：" + val);
            }
        }
        table.close();
        connection.close();
    }


    //获取表所有数据
    public static void getAll(String tablename) throws IOException {
        Connection connection = initHbase();
        Table table = connection.getTable( TableName.valueOf(tablename));
        Scan scan = new Scan();
        ResultScanner results = table.getScanner(scan);
        //Dep dep = new Dep();
        for(Result rs:results){
            Cell[] cells = rs.rawCells();
            for(Cell ce:cells){
                //得到rowkey
//                String s = Bytes.toString(ce.getRowArray(), ce.getRowOffset(), ce.getRowLength());
//                String s2 = Bytes.toString(ce.getRowArray());  //数据输出不对
                String s3 = Bytes.toString(CellUtil.cloneRow(ce));   //效果等同 s
                System.out.println(s3);

                String rowkey = Bytes.toString(CellUtil.cloneRow(ce));
                //得到列
                //方法一：
                String cf = Bytes.toString(CellUtil.cloneFamily(ce));
                String qual = Bytes.toString(CellUtil.cloneQualifier(ce));
                String value = Bytes.toString(CellUtil.cloneValue(ce));
                //方法二：
//                String cf = Bytes.toString(ce.getFamilyArray());
//                String qual = Bytes.toString(ce.getQualifierArray());

                //输出数据
                System.out.println("rowkey：" + rowkey + " ,cd：" + cf + " ,qual：" + qual + " ,value：" + value);



            }
            // System.out.println(rs.toString());  //输出数据格式不对
        }
        table.close();
        connection.close();

    }


    // 单条数据插入方式一：
    public static void  insertdata(String tablename, String row, String cf, String column, String value) throws IOException {
        //创建 HTable对象
        Put put = new Put(Bytes.toBytes(row));
        put.addColumn(Bytes.toBytes(cf), Bytes.toBytes(column), Bytes.toBytes(value));
        //插入表中
        Connection connetion = initHbase();
        Table table = connetion.getTable(TableName.valueOf(tablename));
        table.put(put);

        table.close();
        connetion.close();
    }

    //单条数据插入方式二：  插入一个 dep 对象
    public static void insertDep(String tableName, Dep dep) throws IOException {
        //TableName tablename = TableName.valueOf(tableName);
        Put put = new Put((dep.getId()).getBytes());
        //参数： 列族 列名 值
        put.addColumn("info".getBytes(), "org_name".getBytes(), dep.getOrg_name().getBytes());
        put.addColumn("info".getBytes(), "org_code".getBytes(), dep.getOrg_code().getBytes());
        //插入到表中
        Table table = initHbase().getTable(TableName.valueOf(tableName));
        table.put(put);

    }

    //批量加载
    public static void bullkLoad(){
        Configuration conf = getConf();
        LoadIncrementalHFiles load = new LoadIncrementalHFiles(conf);

    }



    //根据row 进行查询
    public static void getData(String tableName, String rowKey) throws IOException {
        Connection con = initHbase();
        Table table = con.getTable(TableName.valueOf(tableName));
        Get get = new Get(rowKey.getBytes());
        // 先判断是否有此条数据
        if (!get.isCheckExistenceOnly()) {
            Result result = table.get(get);
            for (Cell cell : result.rawCells()) {
                String row = Bytes.toString(cell.getRowArray(), cell.getRowOffset(), cell.getRowLength());
                String qual = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(),
                        cell.getQualifierLength());
                String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                System.out.println("rowkey:" + row + ",qual:" + qual + ",value:" + value);
            }
        }
        table.close();
    }


    //根据row 进行查询
//    public static Dep getData(String tableName, String rowKey) throws IOException {
//        Connection con = initHbase();
//        Table table = con.getTable(TableName.valueOf(tableName));
//        Get get = new Get(rowKey.getBytes());
//        Dep dep = new Dep();
//        //判断是否有此条数据========有待优化，不存在 打印提示标签
//        if(!get.isCheckExistenceOnly()){
//            dep.setId(rowKey);
//            Result result = table.get(get);
//            for(Cell cell : result.rawCells()){
////                String colName = Bytes.toString(cell.getQualifierArray(),cell.getRowOffset(),cell.getRowLength());
////                System.out.println(colName);   //输出 1903
////                String value = Bytes.toString(cell.getValueArray(),cell.getValueOffset(),cell.getValueLength());
////                System.out.println(value);  // 可以输出值
//
//                String qualifier = Bytes.toString(CellUtil.cloneQualifier(cell));
//                String value = Bytes.toString(CellUtil.cloneValue(cell));
//                if(qualifier.equals("org_name")){
//                    dep.setOrg_name(value);
//                }
//                if(qualifier.equals("org_code")){
//                    dep.setOrg_code(value);
//                }
//            }
//        }else{
//            System.out.println("------无此条数据-------");
//        }
//
//        table .close();
//        con.close();
//        return dep;
//    }




}
