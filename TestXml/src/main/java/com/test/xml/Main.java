package com.test.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        InputStream input = Main.class.getResourceAsStream("/book.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(input);
        printNode(document, 0);

        //////////////////
        TestJackson testJackson = new TestJackson();
        testJackson.Test();
    }

    private static void printNode(Node n, int indent) {
        for (int i = 0; i < indent; ++i) {
            System.out.println(" ");
        }

        switch (n.getNodeType()) {
            case Node.DOCUMENT_NODE:
                System.out.println("Document: " + n.getNodeName());
                break;
            case Node.ELEMENT_NODE:
                System.out.println("Element: " + n.getNodeName());
                break;
            case Node.TEXT_NODE:
                System.out.println("Text: " + n.getNodeName() + " = " + n.getNodeValue());
                break;
            case Node.ATTRIBUTE_NODE:
                System.out.println("Arrt: " + n.getNodeName() + " = " + n.getNodeValue());
                break;
            default:
                System.out.println("NodeType: " + n.getNodeType() + ", NodeName: " + n.getNodeName());
        }

        for (Node child = n.getFirstChild(); child != null; child = child.getNextSibling()) {
            printNode(child, indent - 1);
        }
    }
}
