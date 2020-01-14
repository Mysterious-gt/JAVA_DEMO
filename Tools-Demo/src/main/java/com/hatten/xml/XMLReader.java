package com.hatten.xml;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * @Author: jerrylee
 * @Date: 2019/10/25 4:39 下午
 * @Desc: 测试读取xml文件
 */
public class XMLReader {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final Document document = builder.parse("Tools-Demo/src/main/resources/xml/xmltest.xml");
        final NodeList nodes = document.getElementsByTagName("configuration");
        printNode(nodes);
    }

    /**
     * @Desc: 打印节点信息
     * @Author: Jerry
     * @Date: 2020/1/14
     * @Param: [list]
     * @Return: void
     */
    private static void printNode(NodeList list) {
        if (list.getLength() <= 0) {
            return;
        }
        for (int i = 0; i < list.getLength(); i++) {
            //打印子节点
            final Node node = list.item(i);
            //getNodeValue()方法返回值一般都是null，如果需要返回节点的实际值，需要使用getTextContent()方法
            //注意：如果节点中含有子节点，则getTextContent()此方法会将所有子节点中的值都获取到
            System.out.println(node.getNodeName() + " : " + node.getTextContent());

            //打印属性
            final NamedNodeMap attrs = node.getAttributes();
            if (attrs != null) {
                for (int j = 0; j < attrs.getLength(); j++) {
                    final Node attr = attrs.item(j);
                    System.out.println(attr.getNodeName() + " : " + attr.getTextContent());
                }
            }
            final NodeList childs = node.getChildNodes();
            printNode(childs);
        }
    }
}
