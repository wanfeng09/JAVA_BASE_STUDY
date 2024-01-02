package com.file.LogFile;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Properties;

/*
*
* .txt 普通的属性文件。无格式
* .properties 属性文件。键值对格式【admin=123456】-- #测试文件注释
* .xml XML文件。标签格式 -- 注释 <!-- 测试文件注释 -->
* 特殊文件
* 存储多个用户名、密码【.properties】
* 存储用户多个信息【用户名、密码、年龄、爱好...】【.xml】
*
* 读取、写入
* Properties
* Map[接口]--->Properties[实现类]：是一个Map集合，一般不会当集合使用
* 作用：用来代表属性文件，通过Properties可以读写属性文件的内容
* 构造器
* Properties() 构建Properties集合对象（空容器）
* 方法
* load(InputStream in) / load(Reader r) 通过字节（字符）输入流。读取属性文件里的键值对数据
* getProperty(String key) 根据键获取值
* stringPropertyNames() 获取全部键的集合
* setProperty(String key,String value) 保存键值对数据到Properties对象中去
* store(OutputStream out,String comments【注释信息】) / store(Writer w,String comments) 把键值对数据，通过字节（字符）输出流写出到属性文件中去
*
* XML【Extensible Markup Language:可扩展标记语言】
* 本质是一种数据的格式，可以用来存储复杂的数据结构和数据关系
* 特点
* 第一行必须有文档声明 （版本、文件编码）
* 标签成对出现
* 标签名可自定义
* 只有一个根标签
* 标签可以有属性
* 后缀为.xml
*
* 作用
* 存储复杂的数据结构和数据关系
* 常用来做系统的配置文件，或者作为一种特殊的数据结构，在网络中进行传输
*
* 解析XML文件: 开源框架Dom4j
* 下载地址： https://dom4j.github.io/
* 思想：文档对象模型【SAXReader解析器】 Document【整个文档】-Element【元素，标签】-Attribute【属性】-子元素、文本
* 构造器
* SAXReader() 构建Dom4j的解析器对象
* read(String url) 将XML文件读成Document对象
* read(InputStream in) 通过字节输入流读取XML文件
* 方法
* getRootElement() 获取根元素对象
*
* Element提供的方法
* getName() 获取元素名字
* elements() 得到当前元素下的所有子元素
* elements(String name) 指定元素下的所有子元素返回集合
* element(String name) 得到当前元素下指定名字的子元素，多个同名返回第一个
* attributeValue(String name) 通过属性名直接得到属性值
* elementText(子元素名) 得到指定名称的子元素文本
* getText() 得到文本
*
* 写入XML文件
* 不建议Dom4j写入，建议直接把程序的数据拼接成XML格式，然后用IO流写出去
*
* 约束XML文件的书写：就是限制XML文件只能按照某种格式进行书写【限制标签，属性】
* 约束文档类型：DTD文档、Schema文档
* DTD文档
* 编写DTD约束文档，后缀必须是.dtd
* 在需要编写的XML文件中导入DTD约束文档
* 然后XML文件，就必须按照DTD约束文档指定格式进行编写，否则报错。
* 缺点：不能约束具体的数据类型
*
* Schema文档【可以约束数据类型】
* 编写Schema约束文档，后缀必须是.xsd
* 在需要编写的XML文件中导入Schema约束文档
* 按照约束内容编写XML文件的标签
*
* 日志文件
* 之前写的日志就是输出语句，展示在控制台：弊端：不能将日志记录在其他位置，想取消日志，需要修改源代码【注释，或者输出】
*
* 日志技术
* 记录程序运行过程中的各种信息，可指定输出位置【控制台、文件中、数据库中】
* 可以随时以开关的形式控制日志的启停，无需侵入到源代码中进行修改
* 体系结构
* 框架：第三方实现代码，直接用。
* JUL（java.util.logging）/ Log4j / Logback / 其他实现
* 接口：设计日志框架的一套标准，日志框架需要实现这些接口。
* JCL（commons Logging）/ SLF4J （Simple Logging Facade for Java）
*
* JCL < SLF4J : SLF4J是对JCL接口升级
* Log4j < Logback ： Logback是对Log4j性能升级，Logback是基于SLF4J的日志规范实现的框架
*
* Logback日志框架官网地址：https://logback.qos.ch/
* 模块
* logback-core：基础模块，是其他两个模块依赖的基础（必须）
* logback-classic：完整实现了SLF4J的Api模块（必须）
* logback-access：与Tomcat和Jetty等Servlet容器继承，以提供HTTP访问日志的功能（可选）
*
* 实现Logback日志框架：需下载slf4j-api日志接口、logback-core、logback-classic三个jar包
* 在src下新建logback.xml核心配置文件【一般抄别人写好的，不用自己写】
* 创建Logback日志框架提供的Logger对象（注意是slf4j接口下的），然后调用其提供的方法记录系统的日志信息
* 只需创建一次：public static final Logger LOGGER = LoggerFactory.getLogger("类名");
*
* logback.xml核心配置文件基本认识
* 设置输出位置
* <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender"> ... </appender>控制台
* <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender"> ... </appender>系统文件
*
* 开启日志ALL，取消日志OFF
* <root level="ALL">
    <appender-ref="CONSOLE" />
    <appender-ref="FILE" />
* </root>
*
* 日志级别
* 只有日志的级别是大于或者等于核心配置文件配置的日志级别，才会被记录，否则不记录。
*
* 日志信息类型，常见trace<debug<info<warn<error
* |日志级别|说明|
* |trace|追踪，指明程序运行轨迹|
* |debug|调试，实际应用中一般将其作为最低级别，而trace则很少使用|
* |info|输出重要的运行信息，数据连接、网络连接、IO操作等等，使用较多|
* |warn|警告信息，可能会发生问题，使用较多|
* |error|错误信息，使用较多|
*
*
*
*
* */
public class Demo {
    // 创建一个Logger（注意是slf4j接口下的）日志对象
    public static final Logger LOGGER = LoggerFactory.getLogger("Demo");
    public static void main(String[] args) throws Exception {

        // propertyTest(); // 覆盖
        // propertyTest2(); // 追加修改

        // XML
        // XMLReader();
        // XMLWriter();
        // 日志文件
        // while (true){
            logTest(10,0);
        // }
    }
    public static void propertyTest() throws Exception {
        Properties p = new Properties();
        // 写入 【覆盖】
        p.setProperty("test","test1");
        p.setProperty("admin","admin");
        p.setProperty("password","123456");
        p.store(new FileWriter("D:\\java\\demo\\IOtest\\p.properties"),"save");
        // 读取
        p.load(new FileReader("D:\\java\\demo\\IOtest\\p.properties"));
        System.out.println(p);
        System.out.println(p.getProperty("admin"));
        System.out.println(p.stringPropertyNames());
        /*for (String key : p.stringPropertyNames()){
            System.out.println(key + "--------------" + p.getProperty(key));
        }*/
        p.forEach((k,v) -> {
            System.out.println(k + "------>" + v);
        });
    }
    public static void propertyTest2() throws Exception {
        Properties p = new Properties();
        // 读取【已加载】
        p.load(new FileReader("D:\\java\\demo\\IOtest\\p.properties"));
        // 写入新增 【追加】
        p.setProperty("test","test1");
        p.setProperty("test1","admin");
        p.setProperty("testPassword","123456");
        p.store(new FileWriter("D:\\java\\demo\\IOtest\\p.properties"),"save");
    }
    public static void XMLReader() throws Exception {
        SAXReader r = new SAXReader();
        // 获取文档对象
        Document dom =  r.read("src\\com\\file\\LogFile\\helloWorld.xml");
        // 获取根元素对象
        Element root = dom.getRootElement();
        // System.out.println(root.getName()); // Info

        List<Element> list = root.elements(); // 根元素下的所有子元素
        // List<Element> list = root.elements("User");
        for (Element e : list){
            System.out.println(e.getName());
        }
        Element u = root.element("People");
        System.out.println(u.getText()); // 文字测试

        // 获取属性值
        System.out.println(u.attributeValue("id")); // 3
        // 获取属性信息
        Attribute uid = u.attribute("id");
        System.out.println(uid.getName()); // id
        System.out.println(uid.getValue()); // 3

        // 获取所有属性
        List<Attribute> aList  = u.attributes();
        for (Attribute a : aList){
            System.out.println(a.getName() + "--->" + a.getValue());
        }

        Element user = root.element("User");
        // 得到指定名称的子元素文本
        System.out.println(user.elementText("name"));
    }

    public static void XMLWriter(){
        // 构造一个字符串生成器，其中没有字符，初始容量为 16 个字符。
        StringBuilder sb = new StringBuilder();
        // 将指定的字符串追加到此字符序列中。
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n");
        sb.append("<Info>\r\n");
        sb.append("\t<name>").append("测试1").append("</name>\n");
        sb.append("\t<price>").append(1688).append("</price>\n");
        sb.append("</Info>\r\n");
        System.out.println(sb);
        try (
                BufferedWriter bw = new BufferedWriter(new FileWriter("src\\com\\file\\LogFile\\writeTest.xml"))
                ){
            bw.write(sb.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void logTest(int a,int b){
        try {
            LOGGER.info("日志文件开始执行");
            LOGGER.debug("参数a---" + a);
            LOGGER.debug("参数b---" + b);
            int c = a / b;
            LOGGER.info("日志文件执行完成");
        }catch (Exception e){
            LOGGER.error("运行错误");
        }
    }
}
