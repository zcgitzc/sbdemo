@echo off

call mvn -Dmybatis.generator.configurationFile=src/main/resources/generatorConfig-common.xml mybatis-generator:generate

pause
