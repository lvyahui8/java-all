package org.lyh.spring.base.ctx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/7/8 0:16
 */

@Component
public class RunArgs {

    @Autowired
    public RunArgs(ApplicationArguments arguments) {
        // if run with "--debug logfile.txt" debug=true, files=["logfile.txt"]
        boolean debug = arguments.containsOption("debug");
        List<String> files = arguments.getNonOptionArgs();
        files.stream().forEach(System.out::println);
    }
}
