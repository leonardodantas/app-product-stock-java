package com.product.stock;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class GithubActions {

    public static void main(String[] args) {
        System.out.println("Iniciando analise de dependencias");

        try {
            String filePath = "pom.xml";
            File file = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);

            Element rootElement = document.getDocumentElement();

            NodeList propertiesList = rootElement.getElementsByTagName("properties");

            if (propertiesList.getLength() > 0) {
                Element propertiesElement = (Element) propertiesList.item(0);

                NodeList propertyList = propertiesElement.getChildNodes();

                for (int i = 0; i < propertyList.getLength(); i++) {
                    if (propertyList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                        Element propertyElement = (Element) propertyList.item(i);
                        String propertyName = propertyElement.getNodeName();
                        String propertyValue = propertyElement.getTextContent();

                        System.out.println("Property: " + propertyName + " = " + propertyValue);
                    }
                }

            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }
}
