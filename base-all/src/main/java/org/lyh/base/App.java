package org.lyh.base;

import org.claret.utils.NetUtils;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String out = NetUtils.get("http://XXX/get_min_data?idtype=1&begtime=1389110400&endtime=1389111000&datatype=0&query=[{%22id%22:%206314,%20%22attrid%22:%2021},{%22id%22:%206314,%20%22attrid%22:%2031}]");
        System.out.println(out);
    }
}
