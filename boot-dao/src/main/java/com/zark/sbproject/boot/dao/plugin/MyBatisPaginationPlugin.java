package com.zark.sbproject.boot.dao.plugin;

import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.*;
import org.mybatis.generator.codegen.XmlConstants;
import org.mybatis.generator.config.PropertyRegistry;

import java.io.File;
import java.util.*;

/**
 * Mybatis-generator for mysql dataBase
 *
 * @author zark
 * @date 2019-08-12 21:48:27
 */
public class MyBatisPaginationPlugin extends PluginAdapter {

    private static final String XML_FILE_SUFFIX = "Ext";
    private static final String JAVA_FILE_SUFFIX = "Ext";
    private static final String SQL_MAP_COMMON_SUFFIX = "and is_deleted = 'n'";
    private static final String ANNOTATION_RESOURCE = "org.springframework.stereotype.Repository";
    private static final String MAPPER_ANNOTATION = "@Repository";
    private static final String BASIS_CONDITION = " where is_deleted = 'n' ";

    private static final Map<String, String> PROPERTY_FIELD_MAP = new HashMap<>(5);
    private static final Map<String, String> PROPERTY_DEFAULT_VALUE_MAP = new HashMap<>(5);

    public MyBatisPaginationPlugin() {
        super();
        PROPERTY_FIELD_MAP.put("isDeleted", "is_deleted");
        PROPERTY_FIELD_MAP.put("gmtCreate", "gmt_create");
        PROPERTY_FIELD_MAP.put("gmtModified", "gmt_modified");
        PROPERTY_FIELD_MAP.put("modifier", "modifier");
        PROPERTY_FIELD_MAP.put("creator", "creator");

        PROPERTY_DEFAULT_VALUE_MAP.put("isDeleted", "'n'");
        PROPERTY_DEFAULT_VALUE_MAP.put("gmtCreate", "NOW()");
        PROPERTY_DEFAULT_VALUE_MAP.put("gmtModified", "NOW()");
        PROPERTY_DEFAULT_VALUE_MAP.put("modifier", "'system'");
        PROPERTY_DEFAULT_VALUE_MAP.put("creator", "'system'");
    }

    /**
     * 添删改Document的sql语句及属性
     * 修改Mapper.xml中的namespace
     */
    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        XmlElement parentElement = document.getRootElement();
        updateDocumentNameSpace(introspectedTable, parentElement);
        generateMySqlPageSql(parentElement);
        moveDocumentInsertSql(parentElement);
        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

    /**
     * 去除自带的insert
     */
    private void moveDocumentInsertSql(XmlElement parentElement) {
        XmlElement insertElement = null;
        for (Element element : parentElement.getElements()) {
            XmlElement xmlElement = (XmlElement) element;
            if ("insert".equals(xmlElement.getName())) {
                for (Attribute attribute : xmlElement.getAttributes()) {
                    if ("insert".equals(attribute.getValue())) {
                        insertElement = xmlElement;
                        break;
                    }
                }
            }
        }
        parentElement.getElements().remove(insertElement);
    }

    private void updateDocumentNameSpace(IntrospectedTable introspectedTable, XmlElement parentElement) {
        Attribute namespaceAttribute = null;
        for (Attribute attribute : parentElement.getAttributes()) {
            if ("namespace".equals(attribute.getName())) {
                namespaceAttribute = attribute;
            }
        }
        parentElement.getAttributes().remove(namespaceAttribute);
        parentElement.getAttributes()
                .add(new Attribute("namespace", introspectedTable.getMyBatis3JavaMapperType() + JAVA_FILE_SUFFIX));
    }

    /**
     * mysql分页语句后半部分
     */
    private void generateMySqlPageSql(XmlElement parentElement) {
        XmlElement paginationSuffixElement = new XmlElement("sql");
        context.getCommentGenerator().addComment(paginationSuffixElement);
        paginationSuffixElement.addAttribute(new Attribute("id", "MySqlDialectSuffix"));
        XmlElement pageEnd = new XmlElement("if");
        pageEnd.addAttribute(new Attribute("test", "page != null"));
        pageEnd.addElement(new TextElement("<![CDATA[  limit #{page.begin},#{page.end}  ]]>"));
        paginationSuffixElement.addElement(pageEnd);
        parentElement.addElement(paginationSuffixElement);
    }

    /**
     * selectByPrimaryKey
     */
    @Override
    public boolean sqlMapSelectByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        TextElement text = new TextElement(SQL_MAP_COMMON_SUFFIX);
        element.addElement(text);
        return super.sqlMapSelectByPrimaryKeyElementGenerated(element, introspectedTable);
    }

    /**
     * updateByPrimaryKeySelective
     */
    @Override
    public boolean sqlMapUpdateByPrimaryKeySelectiveElementGenerated(XmlElement element,
                                                                     IntrospectedTable introspectedTable) {
        List<Element> elements = element.getElements();
        XmlElement setItem = null;
        int modifierItemIndex = -1;
        int gmtModifiedItemIndex = -1;
        for (Element e : elements) {
            if (e instanceof XmlElement) {
                setItem = (XmlElement) e;
                for (int i = 0; i < setItem.getElements().size(); i++) {
                    XmlElement xmlElement = (XmlElement) setItem.getElements().get(i);
                    for (Attribute att : xmlElement.getAttributes()) {
                        if ("modifier != null".equals(att.getValue())) {
                            modifierItemIndex = i;
                            break;
                        }

                        if ("gmtModified != null".equals(att.getValue())) {
                            gmtModifiedItemIndex = i;
                            break;
                        }

                    }
                }
            }

        }

        if (modifierItemIndex != -1 && setItem != null) {
            addXmlElementModifier(setItem, modifierItemIndex);

        }

        if (gmtModifiedItemIndex != -1 && setItem != null) {
            addXmlElementGmtModified(setItem, gmtModifiedItemIndex);
        }

        TextElement text = new TextElement(SQL_MAP_COMMON_SUFFIX);
        element.addElement(text);
        return super.sqlMapUpdateByPrimaryKeySelectiveElementGenerated(element, introspectedTable);
    }

    private void addXmlElementGmtModified(XmlElement setItem, int gmtModifiedItemIndex) {
        XmlElement defaultGmtModified = new XmlElement("if");
        defaultGmtModified.addAttribute(new Attribute("test", "gmtModified == null"));
        defaultGmtModified.addElement(new TextElement("gmt_modified = NOW(),"));
        setItem.getElements().add(gmtModifiedItemIndex + 1, defaultGmtModified);
    }

    private void addXmlElementModifier(XmlElement setItem, int modifierItemIndex) {
        XmlElement defaultModifier = new XmlElement("if");
        defaultModifier.addAttribute(new Attribute("test", "modifier == null"));
        defaultModifier.addElement(new TextElement("modifier = 'system',"));
        setItem.getElements().add(modifierItemIndex + 1, defaultModifier);
    }

    /**
     * updateByPrimaryKey
     */
    @Override
    public boolean sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(XmlElement element,
                                                                        IntrospectedTable introspectedTable) {
        TextElement text = new TextElement(SQL_MAP_COMMON_SUFFIX);
        element.addElement(text);
        return super.sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(element, introspectedTable);
    }

    /**
     * deleteByPrimaryKey
     */
    @Override
    public boolean sqlMapDeleteByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        element.setName("update");
        int replaceIndex = -1;
        for (int i = 0; i < element.getAttributes().size(); i++) {
            Attribute attr = element.getAttributes().get(i);
            if ("parameterType".equals(attr.getName())) {
                replaceIndex = i;
                break;
            }
        }
        if (replaceIndex >= 0) {
            element.getAttributes().remove(replaceIndex);
            element.getAttributes().add(replaceIndex,
                    new Attribute("parameterType", introspectedTable.getBaseRecordType()));
        }

        element.getElements().clear();

        element.getElements().add(new TextElement("update "
                + introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime()
                + " set is_deleted = 'y',modifier=#{modifier,jdbcType=VARCHAR},gmt_modified=NOW() where ID = #{id,jdbcType=NUMERIC}"));
        return super.sqlMapDeleteByPrimaryKeyElementGenerated(element, introspectedTable);
    }

    /**
     * insertSelective
     */
    @Override
    public boolean sqlMapInsertSelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        Attribute attKeyProperty = new Attribute("keyProperty", "id");
        Attribute attUseGeneratedKeys = new Attribute("useGeneratedKeys", "true");

        element.addAttribute(attKeyProperty);
        element.addAttribute(attUseGeneratedKeys);

        List<Element> elements = element.getElements();
        XmlElement fieldItem = null;
        XmlElement valueItem = null;
        for (Element e : elements) {
            if (!(e instanceof XmlElement)) {
                continue;
            }

            XmlElement xmlElement = (XmlElement) e;
            if ("trim".equals(xmlElement.getName())) {
                for (Attribute attr : xmlElement.getAttributes()) {
                    if ("(".equals(attr.getValue())) {
                        fieldItem = xmlElement;
                        break;
                    }

                    if ("values (".equals(attr.getValue())) {
                        valueItem = xmlElement;
                        break;
                    }
                }
            }

        }

        if (fieldItem != null) {
            addInsertXmlElement(fieldItem, PROPERTY_FIELD_MAP);
        }

        if (valueItem != null) {
            addInsertXmlElement(valueItem, PROPERTY_DEFAULT_VALUE_MAP);
        }

        return super.sqlMapInsertSelectiveElementGenerated(element, introspectedTable);
    }

    private void addInsertXmlElement(XmlElement xmlElement, Map<String, String> mapValues) {
        for (Map.Entry<String, String> entry : mapValues.entrySet()) {
            String property = entry.getKey();
            String fieldOrDefaultValue = entry.getValue();

            XmlElement defaultIsDeleted = new XmlElement("if");
            defaultIsDeleted.addAttribute(new Attribute("test", property + " == null"));
            defaultIsDeleted.addElement(new TextElement(fieldOrDefaultValue + ","));
            xmlElement.addElement(1, defaultIsDeleted);
        }
    }

    @Override
    public boolean clientDeleteByPrimaryKeyMethodGenerated(Method method, Interface interfaze,
                                                           IntrospectedTable introspectedTable) {
        Parameter parameter = new Parameter(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()),
                "record");
        method.getParameters().clear();
        method.addParameter(parameter);
        return super.clientDeleteByPrimaryKeyMethodGenerated(method, interfaze, introspectedTable);
    }

    @Override
    public boolean clientGenerated(Interface anInterface, TopLevelClass topLevelClass,
                                   IntrospectedTable introspectedTable) {
        List<Method> methods = anInterface.getMethods();
        Method insertMethod = null;
        for (Method method : methods) {
            if ("insert".equals(method.getName())) {
                insertMethod = method;
                break;
            }
        }
        methods.remove(insertMethod);

        Method updateMethod = null;
        for (Method method : methods) {
            if ("updateByPrimaryKey".equals(method.getName())) {
                updateMethod = method;
                break;
            }
        }
        methods.remove(updateMethod);

        return super.clientGenerated(anInterface, topLevelClass, introspectedTable);
    }

    /**
     * selectByExample
     */
    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element,
                                                                     IntrospectedTable introspectedTable) {

        XmlElement lastXmlE = (XmlElement) element.getElements().remove(element.getElements().size() - 1);

        XmlElement isDeletedElement = new XmlElement("if");
        isDeletedElement.addAttribute(new Attribute("test", " null!=oredCriteria and oredCriteria.size != 0"));
        isDeletedElement.addElement(new TextElement(SQL_MAP_COMMON_SUFFIX));
        element.addElement(isDeletedElement);

        isDeletedElement = new XmlElement("if");
        isDeletedElement.addAttribute(new Attribute("test", " null!=oredCriteria and oredCriteria.size == 0"));
        isDeletedElement.addElement(new TextElement(BASIS_CONDITION));
        element.addElement(isDeletedElement);

        element.addElement(lastXmlE);

        return super.sqlMapSelectByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
    }

    /**
     * countByExample
     */
    @Override
    public boolean sqlMapCountByExampleElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        XmlElement isNotNullElement = new XmlElement("if");
        isNotNullElement.addAttribute(new Attribute("test", " null != oredCriteria and oredCriteria.size != 0"));
        isNotNullElement.addElement(new TextElement(SQL_MAP_COMMON_SUFFIX));
        element.addElement(isNotNullElement);
        isNotNullElement = new XmlElement("if");
        isNotNullElement.addAttribute(new Attribute("test", " null != oredCriteria and oredCriteria.size == 0"));
        isNotNullElement.addElement(new TextElement(BASIS_CONDITION));
        element.addElement(isNotNullElement);

        return super.sqlMapCountByExampleElementGenerated(element, introspectedTable);
    }

    /**
     * 生成XXExt.xml
     */
    @Override
    public List<GeneratedXmlFile> contextGenerateAdditionalXmlFiles(IntrospectedTable introspectedTable) {

        String[] splitFile = introspectedTable.getMyBatis3XmlMapperFileName().split("\\.");
        String fileNameExt = null;
        if (splitFile[0] != null) {
            fileNameExt = splitFile[0] + XML_FILE_SUFFIX + ".xml";
        }

        if (isExistExtFile(context.getSqlMapGeneratorConfiguration().getTargetProject(),
                introspectedTable.getMyBatis3XmlMapperPackage(), fileNameExt)) {
            return super.contextGenerateAdditionalXmlFiles(introspectedTable);
        }

        Document document = new Document(XmlConstants.MYBATIS3_MAPPER_PUBLIC_ID,
                XmlConstants.MYBATIS3_MAPPER_SYSTEM_ID);

        XmlElement root = new XmlElement("mapper");
        document.setRootElement(root);
        String namespace = introspectedTable.getMyBatis3SqlMapNamespace() + XML_FILE_SUFFIX;
        root.addAttribute(new Attribute("namespace", namespace));

        GeneratedXmlFile gxf = new GeneratedXmlFile(document, fileNameExt,
                introspectedTable.getMyBatis3XmlMapperPackage(),
                context.getSqlMapGeneratorConfiguration().getTargetProject(), false, context.getXmlFormatter());

        List<GeneratedXmlFile> answer = new ArrayList(1);
        answer.add(gxf);

        return answer;
    }

    /**
     * 生成XXExt.java
     */
    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {

        FullyQualifiedJavaType type = new FullyQualifiedJavaType(
                introspectedTable.getMyBatis3JavaMapperType() + JAVA_FILE_SUFFIX);
        Interface anInterface = new Interface(type);
        anInterface.setVisibility(JavaVisibility.PUBLIC);
        context.getCommentGenerator().addJavaFileComment(anInterface);

        FullyQualifiedJavaType baseInterfaze = new FullyQualifiedJavaType(
                introspectedTable.getMyBatis3JavaMapperType());
        anInterface.addSuperInterface(baseInterfaze);

        FullyQualifiedJavaType annotation = new FullyQualifiedJavaType(ANNOTATION_RESOURCE);
        anInterface.addAnnotation(MAPPER_ANNOTATION);
        anInterface.addImportedType(annotation);

        GeneratedJavaFile generatedJavaFile = new GeneratedJavaFile(anInterface,
                context.getJavaModelGeneratorConfiguration().getTargetProject(),
                context.getProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING), context.getJavaFormatter());

        if (isExistExtFile(generatedJavaFile.getTargetProject(), generatedJavaFile.getTargetPackage(),
                generatedJavaFile.getFileName())) {
            return super.contextGenerateAdditionalJavaFiles(introspectedTable);
        }
        List<GeneratedJavaFile> generatedJavaFiles = new ArrayList(1);
        generatedJavaFile.getFileName();
        generatedJavaFiles.add(generatedJavaFile);
        return generatedJavaFiles;
    }

    private boolean isExistExtFile(String targetProject, String targetPackage, String fileName) {

        File project = new File(targetProject);
        if (!project.isDirectory()) {
            return true;
        }

        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(targetPackage, ".");
        while (st.hasMoreTokens()) {
            sb.append(st.nextToken());
            sb.append(File.separatorChar);
        }

        File directory = new File(project, sb.toString());
        if (!directory.isDirectory()) {
            boolean rc = directory.mkdirs();
            if (!rc) {
                return true;
            }
        }

        File testFile = new File(directory, fileName);
        if (testFile.exists()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable) {
        try {
            java.lang.reflect.Field field = sqlMap.getClass().getDeclaredField("isMergeable");
            field.setAccessible(true);
            field.setBoolean(sqlMap, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


    public static void main(String[] args) throws Exception {
//        String configUser = MyBatisPaginationPlugin.class.getClassLoader()
//                .getResource("generatorConfig-user.xml").getFile();
//        String[] argUser = {"-configfile", configUser, "-overwrite"};
//        ShellRunner.main(argUser);

        String configCommon = MyBatisPaginationPlugin.class.getClassLoader()
                .getResource("generatorConfig-common.xml").getFile();
        String[] argCommon = {"-configfile", configCommon, "-overwrite"};
        ShellRunner.main(argCommon);
    }

}