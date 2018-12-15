package com.zark.sbproject.boot.entity.plugin;

import static org.mybatis.generator.internal.util.StringUtility.isTrue;

import java.io.File;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.ShellRunner;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.InnerEnum;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaElement;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.XmlConstants;
import org.mybatis.generator.config.MergeConstants;
import org.mybatis.generator.config.PropertyRegistry;

/**
 * Mybatis-generator for mysql dataBase
 *
 */
public class MyBatisPaginationPlugin extends PluginAdapter implements CommentGenerator {

	private static String XMLFILE_POSTFIX = "Ext";
	private static String JAVAFILE_POTFIX = "Ext";
	private static String SQLMAP_COMMON_POTFIX = "and is_deleted = 'n'";
	private static String ANNOTATION_RESOURCE = "org.springframework.stereotype.Repository";
	private static String MAPPER_ANNOTATION = "@Repository";
	private static String BAISE_CONDITION = " where is_deleted = 'n' ";

	private Properties properties;
	private Properties systemPro;
	private boolean suppressDate;
	private boolean suppressAllComments;
	private String currentDateStr;

	public MyBatisPaginationPlugin() {
		super();
		properties = new Properties();
		systemPro = System.getProperties();
		suppressDate = false;
		suppressAllComments = false;
		currentDateStr = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
	}

	// 添删改Document的sql语句及属性
	@Override
	public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
		XmlElement parentElement = document.getRootElement();
		// 修改Mapper.xml中的namespace
		updateDocumentNameSpace(introspectedTable, parentElement);
		generateMySqlPageSql(parentElement);
		moveDocumentInsertSql(parentElement);
		return super.sqlMapDocumentGenerated(document, introspectedTable);
	}

	// 去除自带的insert
	private void moveDocumentInsertSql(XmlElement parentElement) {
		XmlElement insertElement = null;
		for (Element element : parentElement.getElements()) {
			XmlElement xmlElement = (XmlElement) element;
			if (xmlElement.getName().equals("insert")) {
				for (Attribute attribute : xmlElement.getAttributes()) {
					if (attribute.getValue().equals("insert")) {
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
			if (attribute.getName().equals("namespace")) {
				namespaceAttribute = attribute;
			}
		}
		parentElement.getAttributes().remove(namespaceAttribute);
		parentElement.getAttributes()
				.add(new Attribute("namespace", introspectedTable.getMyBatis3JavaMapperType() + JAVAFILE_POTFIX));
	}

	private void generateMySqlPageSql(XmlElement parentElement) {
		// mysql分页语句后半部分
		XmlElement paginationSuffixElement = new XmlElement("sql");
		context.getCommentGenerator().addComment(paginationSuffixElement);
		paginationSuffixElement.addAttribute(new Attribute("id", "MySqlDialectSuffix"));
		XmlElement pageEnd = new XmlElement("if");
		pageEnd.addAttribute(new Attribute("test", "page != null"));
		pageEnd.addElement(new TextElement("<![CDATA[  limit #{page.begin},#{page.end}  ]]>"));
		paginationSuffixElement.addElement(pageEnd);
		parentElement.addElement(paginationSuffixElement);
	}

	// selectByPrimaryKey
	@Override
	public boolean sqlMapSelectByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		TextElement text = new TextElement(SQLMAP_COMMON_POTFIX);
		element.addElement(text);
		return super.sqlMapSelectByPrimaryKeyElementGenerated(element, introspectedTable);
	}

	// updateByPrimaryKeySelective
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
						if (att.getValue().equals("modifier != null")) {
							modifierItemIndex = i;
							break;
						}

						if (att.getValue().equals("gmtModified != null")) {
							gmtModifiedItemIndex = i;
							break;
						}

					}
				}
			}

		}

		if (modifierItemIndex != -1 && setItem != null) {
			addXmlElementmodifier(setItem, modifierItemIndex);

		}

		if (gmtModifiedItemIndex != -1 && setItem != null) {
			addXmlElementgmtModified(setItem, gmtModifiedItemIndex);
		}

		TextElement text = new TextElement(SQLMAP_COMMON_POTFIX);
		element.addElement(text);
		return super.sqlMapUpdateByPrimaryKeySelectiveElementGenerated(element, introspectedTable);
	}

	private void addXmlElementgmtModified(XmlElement setItem, int gmtModifiedItemIndex) {
		XmlElement defaultGmtModified = new XmlElement("if");
		defaultGmtModified.addAttribute(new Attribute("test", "gmtModified == null"));
		defaultGmtModified.addElement(new TextElement("gmt_modified = NOW(),"));
		setItem.getElements().add(gmtModifiedItemIndex + 1, defaultGmtModified);
	}

	private void addXmlElementmodifier(XmlElement setItem, int modifierItemIndex) {
		XmlElement defaultmodifier = new XmlElement("if");
		defaultmodifier.addAttribute(new Attribute("test", "modifier == null"));
		defaultmodifier.addElement(new TextElement("modifier = 'system',"));
		setItem.getElements().add(modifierItemIndex + 1, defaultmodifier);
	}

	// updateByPrimaryKey
	@Override
	public boolean sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {
		TextElement text = new TextElement(SQLMAP_COMMON_POTFIX);
		element.addElement(text);
		return super.sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(element, introspectedTable);
	}

	// deleteByPrimaryKey
	@Override
	public boolean sqlMapDeleteByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		element.setName("update");
		int replaceInd = -1;
		for (int i = 0; i < element.getAttributes().size(); i++) {
			Attribute attr = element.getAttributes().get(i);
			if (attr.getName().equals("parameterType")) {
				replaceInd = i;
				break;
			}
		}
		if (replaceInd >= 0) {
			element.getAttributes().remove(replaceInd);
			element.getAttributes().add(replaceInd,
					new Attribute("parameterType", introspectedTable.getBaseRecordType()));
		}

		List<Element> removeElement = new ArrayList<Element>();
		for (int i = 0; i < element.getElements().size(); i++) {
			removeElement.add(element.getElements().get(i));

		}
		element.getElements().removeAll(removeElement);

		element.getElements().add(new TextElement("update "
				+ introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime()
				+ " set is_deleted = 'y',modifier=#{modifier,jdbcType=VARCHAR},gmt_modified=NOW() where ID = #{id,jdbcType=NUMERIC}"));
		return super.sqlMapDeleteByPrimaryKeyElementGenerated(element, introspectedTable);
	}

	// insertSelective
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
			if (e instanceof XmlElement) {
				XmlElement xmlElement = (XmlElement) e;
				if (xmlElement.getName().equals("trim")) {
					for (Attribute arr : xmlElement.getAttributes()) {
						if (arr.getValue().equals("(")) {
							fieldItem = xmlElement;
							break;
						}

						if (arr.getValue().equals("values (")) {
							valueItem = xmlElement;
							break;
						}
					}
				}
			}
		}

		if (fieldItem != null) {
			XmlElement defaultIsDeleted = new XmlElement("if");
			defaultIsDeleted.addAttribute(new Attribute("test", "isDeleted == null"));
			defaultIsDeleted.addElement(new TextElement("is_deleted,"));
			fieldItem.addElement(1, defaultIsDeleted);

			XmlElement defaultGmtModified = new XmlElement("if");
			defaultGmtModified.addAttribute(new Attribute("test", "gmtCreate == null"));
			defaultGmtModified.addElement(new TextElement("gmt_create,"));
			fieldItem.addElement(1, defaultGmtModified);

			XmlElement defaultmodifier = new XmlElement("if");
			defaultmodifier.addAttribute(new Attribute("test", "gmtModified == null"));
			defaultmodifier.addElement(new TextElement("gmt_modified,"));
			fieldItem.addElement(1, defaultmodifier);

			XmlElement defaultGmtCreate = new XmlElement("if");
			defaultGmtCreate.addAttribute(new Attribute("test", "modifier == null"));
			defaultGmtCreate.addElement(new TextElement("modifier,"));
			fieldItem.addElement(1, defaultGmtCreate);

			XmlElement defaultCreator = new XmlElement("if");
			defaultCreator.addAttribute(new Attribute("test", "creator == null"));
			defaultCreator.addElement(new TextElement("creator,"));
			fieldItem.addElement(1, defaultCreator);
		}

		if (valueItem != null) {
			XmlElement defaultIsDeleted = new XmlElement("if");
			defaultIsDeleted.addAttribute(new Attribute("test", "isDeleted == null"));
			defaultIsDeleted.addElement(new TextElement("'n',"));
			valueItem.addElement(1, defaultIsDeleted);

			XmlElement defaultGmtCreate = new XmlElement("if");
			defaultGmtCreate.addAttribute(new Attribute("test", "gmtCreate == null"));
			defaultGmtCreate.addElement(new TextElement("NOW(),"));
			valueItem.addElement(1, defaultGmtCreate);

			XmlElement defaultmodifier = new XmlElement("if");
			defaultmodifier.addAttribute(new Attribute("test", "gmtModified == null"));
			defaultmodifier.addElement(new TextElement("NOW(),"));
			valueItem.addElement(1, defaultmodifier);

			XmlElement defaultGmtModified = new XmlElement("if");
			defaultGmtModified.addAttribute(new Attribute("test", "modifier == null"));
			defaultGmtModified.addElement(new TextElement("'system',"));
			valueItem.addElement(1, defaultGmtModified);

			XmlElement defaultCreator = new XmlElement("if");
			defaultCreator.addAttribute(new Attribute("test", "creator == null"));
			defaultCreator.addElement(new TextElement("'system',"));
			valueItem.addElement(1, defaultCreator);

		}

		return super.sqlMapInsertSelectiveElementGenerated(element, introspectedTable);
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
	public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		List<Method> methods = interfaze.getMethods();
		Method insertMethod = null;
		for (Method method : methods) {
			if (method.getName().equals("insert")) {
				insertMethod = method;
				break;
			}
		}
		methods.remove(insertMethod);

		Method updateMethod = null;
		for (Method method : methods) {
			if (method.getName().equals("updateByPrimaryKey")) {
				updateMethod = method;
				break;
			}
		}
		methods.remove(updateMethod);

		return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
	}

	// selectByExample
	@Override
	public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {

		XmlElement lastXmlE = (XmlElement) element.getElements().remove(element.getElements().size() - 1);

		XmlElement isdeletedElement = new XmlElement("if");
		isdeletedElement.addAttribute(new Attribute("test", " null!=oredCriteria and oredCriteria.size != 0"));
		isdeletedElement.addElement(new TextElement(SQLMAP_COMMON_POTFIX));
		element.addElement(isdeletedElement);

		isdeletedElement = new XmlElement("if");
		isdeletedElement.addAttribute(new Attribute("test", " null!=oredCriteria and oredCriteria.size == 0"));
		isdeletedElement.addElement(new TextElement(BAISE_CONDITION));
		element.addElement(isdeletedElement);

		element.addElement(lastXmlE);

		return super.sqlMapSelectByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
	}

	// countByExample
	@Override
	public boolean sqlMapCountByExampleElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		XmlElement isNotNullElement = new XmlElement("if");
		isNotNullElement.addAttribute(new Attribute("test", " null!=oredCriteria and oredCriteria.size != 0"));
		isNotNullElement.addElement(new TextElement(SQLMAP_COMMON_POTFIX));
		element.addElement(isNotNullElement);
		isNotNullElement = new XmlElement("if");
		isNotNullElement.addAttribute(new Attribute("test", " null!=oredCriteria and oredCriteria.size == 0"));
		isNotNullElement.addElement(new TextElement(BAISE_CONDITION));
		element.addElement(isNotNullElement);

		return super.sqlMapCountByExampleElementGenerated(element, introspectedTable);
	}

	// 生成XXExt.xml
	@Override
	public List<GeneratedXmlFile> contextGenerateAdditionalXmlFiles(IntrospectedTable introspectedTable) {

		String[] splitFile = introspectedTable.getMyBatis3XmlMapperFileName().split("\\.");
		String fileNameExt = null;
		if (splitFile[0] != null) {
			fileNameExt = splitFile[0] + XMLFILE_POSTFIX + ".xml";
		}

		if (isExistExtFile(context.getSqlMapGeneratorConfiguration().getTargetProject(),
				introspectedTable.getMyBatis3XmlMapperPackage(), fileNameExt)) {
			return super.contextGenerateAdditionalXmlFiles(introspectedTable);
		}

		Document document = new Document(XmlConstants.MYBATIS3_MAPPER_PUBLIC_ID,
				XmlConstants.MYBATIS3_MAPPER_SYSTEM_ID);

		XmlElement root = new XmlElement("mapper");
		document.setRootElement(root);
		String namespace = introspectedTable.getMyBatis3SqlMapNamespace() + XMLFILE_POSTFIX;
		root.addAttribute(new Attribute("namespace", namespace));

		GeneratedXmlFile gxf = new GeneratedXmlFile(document, fileNameExt,
				introspectedTable.getMyBatis3XmlMapperPackage(),
				context.getSqlMapGeneratorConfiguration().getTargetProject(), false, context.getXmlFormatter());

		List<GeneratedXmlFile> answer = new ArrayList<GeneratedXmlFile>(1);
		answer.add(gxf);

		return answer;
	}

	// 生成XXExt.java
	@Override
	public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {

		FullyQualifiedJavaType type = new FullyQualifiedJavaType(
				introspectedTable.getMyBatis3JavaMapperType() + JAVAFILE_POTFIX);
		Interface interfaze = new Interface(type);
		interfaze.setVisibility(JavaVisibility.PUBLIC);
		context.getCommentGenerator().addJavaFileComment(interfaze);

		FullyQualifiedJavaType baseInterfaze = new FullyQualifiedJavaType(
				introspectedTable.getMyBatis3JavaMapperType());
		interfaze.addSuperInterface(baseInterfaze);

		FullyQualifiedJavaType annotation = new FullyQualifiedJavaType(ANNOTATION_RESOURCE);
		interfaze.addAnnotation(MAPPER_ANNOTATION);
		interfaze.addImportedType(annotation);

		CompilationUnit compilationUnits = interfaze;
		GeneratedJavaFile generatedJavaFile = new GeneratedJavaFile(compilationUnits,
				context.getJavaModelGeneratorConfiguration().getTargetProject(),
				context.getProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING), context.getJavaFormatter());

		if (isExistExtFile(generatedJavaFile.getTargetProject(), generatedJavaFile.getTargetPackage(),
				generatedJavaFile.getFileName())) {
			return super.contextGenerateAdditionalJavaFiles(introspectedTable);
		}
		List<GeneratedJavaFile> generatedJavaFiles = new ArrayList<GeneratedJavaFile>(1);
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
			Field field = sqlMap.getClass().getDeclaredField("isMergeable");
			field.setAccessible(true);
			field.setBoolean(sqlMap, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public void addJavaFileComment(CompilationUnit compilationUnit) {
		// add no file level comments by default
		return;
	}

	/**
	 * Adds a suitable comment to warn users that the element was generated, and
	 * when it was generated.
	 */
	@Override
	public void addComment(XmlElement xmlElement) {
		return;
	}

	@Override
	public void addRootComment(XmlElement rootElement) {
		// add no document level comments by default
		return;
	}

	@Override
	public void addConfigurationProperties(Properties properties) {
		this.properties.putAll(properties);

		suppressDate = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_DATE));

		suppressAllComments = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_ALL_COMMENTS));
	}

	/**
	 * This method adds the custom javadoc tag for. You may do nothing if you do not
	 * wish to include the Javadoc tag - however, if you do not include the Javadoc
	 * tag then the Java merge capability of the eclipse plugin will break.
	 * 
	 * @param javaElement the java element
	 */
	protected void addJavadocTag(JavaElement javaElement, boolean markAsDoNotDelete) {
		javaElement.addJavaDocLine(" *");
		StringBuilder sb = new StringBuilder();
		sb.append(" * ");
		sb.append(MergeConstants.NEW_ELEMENT_TAG);
		if (markAsDoNotDelete) {
			sb.append(" do_not_delete_during_merge");
		}
		String s = getDateString();
		if (s != null) {
			sb.append(' ');
			sb.append(s);
		}
		javaElement.addJavaDocLine(sb.toString());
	}

	/**
	 * This method returns a formated date string to include in the Javadoc tag and
	 * XML comments. You may return null if you do not want the date in these
	 * documentation elements.
	 * 
	 * @return a string representing the current timestamp, or null
	 */
	protected String getDateString() {
		String result = null;
		if (!suppressDate) {
			result = currentDateStr;
		}
		return result;
	}

	@Override
	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
		if (suppressAllComments) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		innerClass.addJavaDocLine("/**");
		sb.append(" * ");
		sb.append(introspectedTable.getFullyQualifiedTable());
		sb.append(" ");
		sb.append(getDateString());
		innerClass.addJavaDocLine(sb.toString());
		innerClass.addJavaDocLine(" */");
	}

	@Override
	public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
		if (suppressAllComments) {
			return;
		}

		StringBuilder sb = new StringBuilder();

		innerEnum.addJavaDocLine("/**");
		addJavadocTag(innerEnum, false);
		sb.append(" * ");
		sb.append(introspectedTable.getFullyQualifiedTable());
		innerEnum.addJavaDocLine(sb.toString());
		innerEnum.addJavaDocLine(" */");
	}

	@Override
	public void addFieldComment(org.mybatis.generator.api.dom.java.Field field, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if (suppressAllComments) {
			return;
		}

		StringBuilder sb = new StringBuilder();

		field.addJavaDocLine("/**");
		sb.append(" * ");
		sb.append(introspectedColumn.getRemarks());
		field.addJavaDocLine(sb.toString());

		addJavadocTag(field, false);

		field.addJavaDocLine(" */");
	}

	@Override
	public void addFieldComment(org.mybatis.generator.api.dom.java.Field field, IntrospectedTable introspectedTable) {
		if (suppressAllComments) {
			return;
		}

		StringBuilder sb = new StringBuilder();

		field.addJavaDocLine("/**");
		sb.append(" * ");
		sb.append(introspectedTable.getFullyQualifiedTable());
		field.addJavaDocLine(sb.toString());
		field.addJavaDocLine(" */");
	}

	@Override
	public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
		if (suppressAllComments) {
			return;
		}
		method.addJavaDocLine("/**");
		addJavadocTag(method, false);
		method.addJavaDocLine(" */");
	}

	@Override
	public void addGetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if (suppressAllComments) {
			return;
		}

		method.addJavaDocLine("/**");

		StringBuilder sb = new StringBuilder();
		sb.append(" * ");
		sb.append(introspectedColumn.getRemarks());
		method.addJavaDocLine(sb.toString());

		sb.setLength(0);
		sb.append(" * @return ");
		sb.append(introspectedColumn.getActualColumnName());
		sb.append(" ");
		sb.append(introspectedColumn.getRemarks());
		method.addJavaDocLine(sb.toString());

		addJavadocTag(method, false);

		method.addJavaDocLine(" */");
	}

	@Override
	public void addSetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if (suppressAllComments) {
			return;
		}

		method.addJavaDocLine("/**");
		StringBuilder sb = new StringBuilder();
		sb.append(" * ");
		sb.append(introspectedColumn.getRemarks());
		method.addJavaDocLine(sb.toString());

		Parameter parm = method.getParameters().get(0);
		sb.setLength(0);
		sb.append(" * @param ");
		sb.append(parm.getName());
		sb.append(" ");
		sb.append(introspectedColumn.getRemarks());
		method.addJavaDocLine(sb.toString());

		addJavadocTag(method, false);

		method.addJavaDocLine(" */");
	}

	@Override
	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
		if (suppressAllComments) {
			return;
		}

		StringBuilder sb = new StringBuilder();

		innerClass.addJavaDocLine("/**");
		sb.append(" * ");
		sb.append(introspectedTable.getFullyQualifiedTable());
		innerClass.addJavaDocLine(sb.toString());

		sb.setLength(0);
		sb.append(" * @author ");
		sb.append(systemPro.getProperty("user.name"));
		sb.append(" ");
		sb.append(currentDateStr);

		addJavadocTag(innerClass, markAsDoNotDelete);

		innerClass.addJavaDocLine(" */");
	}

	public static void main(String[] args) {
		String config = MyBatisPaginationPlugin.class.getClassLoader().getResource("generatorConfig-conf.xml")
				.getFile();
		String[] arg = { "-configfile", config, "-overwrite" };
		ShellRunner.main(arg);

		String config2 = MyBatisPaginationPlugin.class.getClassLoader().getResource("generatorConfig-common.xml")
				.getFile();
		String[] arg2 = { "-configfile", config2, "-overwrite" };
		ShellRunner.main(arg2);
	}

}
