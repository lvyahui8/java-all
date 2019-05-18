@echo off
mvn compile
mvn exec:java -Dexec.mainClass=org.apache.camel.main.Main -Dexec.args='-r org.lyh.camel.builder.TimerRouteBuilder'