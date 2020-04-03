package utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;

public class HbaseConnect {
    //连接集群
    public static Connection initHbase() throws IOException {
        System.setProperty("HADOOP_USER_NAME","hadoop");
        System.setProperty("user.name","hadoop");

        Configuration configuration = HBaseConfiguration.create();
        //configuration.set("zookeeper.znode.parent","/hbase-unsecure");
        configuration.set("hbase.zookeeper.property.clientPort","2181");
        configuration.set("hbase.zookeeper.quorum","192.168.0.104,192.168.0.105,192.168.0.106");
        configuration.set("hbase.master","192.168.0.103:16000");

        Connection connetion = ConnectionFactory.createConnection(configuration);

        return connetion;

    }


    public static  Configuration getConf(){
        System.setProperty("HADOOP_USER_NAME","hadoop");
        System.setProperty("user.name","hadoop");

        Configuration conf = HBaseConfiguration.create();
        //conf.set("zookeeper.znode.parent","/hbase-unsecure");
        conf.set("hbase.zookeeper.property.clientPort","2181");
        conf.set("hbase.zookeeper.quorum","192.168.0.103");
        conf.set("hbase.master","192.168.0.103:16000");

        return conf;
    }

}
