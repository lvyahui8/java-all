package org.lyh.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{

    public static final Logger logger  = LoggerFactory.getLogger(App.class);

    public static final String HBASE_CONFIGURATION_ZOOKEEPER_QUORUM     = "hbase.zookeeper.quorum";
    public static final String HBASE_CONFIGURATION_ZOOKEEPER_CLIENTPORT     = "hbase.zookeeper.property.clientPort";

    public static void main( String[] args ) throws IOException {

        String hadoopHome=System.getenv("HADOOP_HOME");
        String devDefault=new File("D:\\app\\hadoop-2.9.0").getAbsolutePath();
        System.setProperty("hadoop.home.dir", hadoopHome==null ? devDefault:hadoopHome);


        Configuration configuration = HBaseConfiguration.create();

        configuration.setInt("timeout", 120000);
        String host = "127.0.0.1";
        //configuration.set("hbase.master", "*" +  host + ":9000*");
        configuration.set(HBASE_CONFIGURATION_ZOOKEEPER_QUORUM, host);
        configuration.setInt(HBASE_CONFIGURATION_ZOOKEEPER_CLIENTPORT, 2181);

        Connection connection = ConnectionFactory.createConnection(configuration);
        Table mytable = null;
        try{
            mytable = connection.getTable(TableName.valueOf("mytable"));
            logger.info("mytable : {}",mytable.toString());
            Put put = new Put("six".getBytes());
            put.addColumn("cf".getBytes(),"age".getBytes(), Bytes.toBytes(12));
            mytable.put(put);

            Scan scan = new Scan();
            ResultScanner rs = null;
            try
            {
                rs = mytable.getScanner(scan);
                for (Result  r  : rs){
                    for (Cell cell : r.listCells()){
                        logger.info(cell.toString());
                    }
                }
            }finally {
                if(rs != null) rs.close();
            }

        } finally {
            if(mytable != null){
                mytable.close();
            }
            connection.close();
        }

    }
}
