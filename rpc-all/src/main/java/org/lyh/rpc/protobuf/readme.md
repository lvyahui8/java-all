开发者文档中文版，内容翻译不全
http://blog.csdn.net/menuconfig/article/details/12837173

在java中使用protobuf
http://blog.csdn.net/yzzky/article/details/44650825

github 地址，及最新版本下载。我下的是win32得到 protoc.exe文件
https://github.com/google/protobuf/releases/tag/v3.4.0

编写参考另外一篇文章
http://blog.csdn.net/hiz1990/article/details/48198631
http://cq520.iteye.com/blog/2025147


编译

在目录
E:\Work\java-all\rpc-all\src\main\java> 
下执行
C:\soft\protoc-3.4.0-win32\bin\protoc.exe --java_out=./ org\lyh\rpc\protobuf\Person.proto
输出
[libprotobuf WARNING google/protobuf/compiler/parser.cc:546] No syntax specified for the proto file: org/lyh/rpc/protobuf/Person.proto. 
Please use 'syntax = "proto2";' or 'syntax = "proto3";' to specify a syntax version. (Defaulted to proto2 syntax.)

上述警告是指要在Person.proto文件中指定语法版本
option syntax = "proto2";
或者
option syntax = "proto3";
而且要加在最前面