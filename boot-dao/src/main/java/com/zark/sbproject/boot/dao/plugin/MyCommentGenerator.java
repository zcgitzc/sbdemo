package com.zark.sbproject.boot.dao.plugin;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.internal.DefaultCommentGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @author zark
 * @date 2019-08-12
 */
public class MyCommentGenerator extends DefaultCommentGenerator {

    private Properties properties;


    public MyCommentGenerator() {
        properties = new Properties();
    }

    @Override
    public void addConfigurationProperties(Properties properties) {
        // 获取自定义的 properties
        this.properties.putAll(properties);
        this.properties.putAll(System.getProperties());
    }


    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        String author = properties.getProperty("user.name");
        String dateFormat = properties.getProperty("dateFormat", "yyyy-MM-dd");
        SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat);

        // 获取表注释
        String remarks = introspectedTable.getRemarks();

        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * " + remarks);
        topLevelClass.addJavaDocLine(" *");
        topLevelClass.addJavaDocLine(" * @author " + author);
        topLevelClass.addJavaDocLine(" * @date   " + dateFormatter.format(new Date()));
        topLevelClass.addJavaDocLine(" */");
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        // 获取列注释
        String remarks = introspectedColumn.getRemarks();
        field.addJavaDocLine("/**");
        field.addJavaDocLine(" * " + remarks);
        field.addJavaDocLine(" */");
    }

    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        addComment(method, introspectedColumn, OperateType.GETTER);
    }

    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        addComment(method, introspectedColumn, OperateType.SETTER);
    }

    private void addComment(JavaElement javaElement,
                            IntrospectedColumn introspectedColumn, OperateType operateType) {
        Method method = (Method) javaElement;

        method.addJavaDocLine("/**");
        method.addJavaDocLine(" * " + introspectedColumn.getRemarks());

        if (OperateType.GETTER.equals(operateType)) {
            method.addJavaDocLine(" * @return " + introspectedColumn.getActualColumnName());
        } else if (OperateType.SETTER.equals(operateType)) {
            Parameter param = method.getParameters().get(0);
            method.addJavaDocLine("* @param " + param.getName());
        }

        method.addJavaDocLine(" */");
    }


    enum OperateType {
        /**
         * Get方法
         */
        GETTER,

        /**
         * Set方法
         */
        SETTER
    }
}
