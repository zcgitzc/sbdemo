@echo off

call mvn -Dmybatis.generator.configurationFile=src/main/resources/generatorConfig-conf.xml mybatis-generator:generate

pause
