package com.converter.data;

import com.converter.model.Currency;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

public class ParserXML {
    public static Set<Currency> parse() {
        try {
            InputStream inputStream = new URL("http://www.cbr.ru/scripts/XML_daily.asp").openStream();
            Files.copy(inputStream, Paths.get("./src/main/java/com/converter/data/currency.xml"), StandardCopyOption.REPLACE_EXISTING);
            File xmlFile = new File("./src/main/java/com/converter/data/currency.xml");
            if (xmlFile.exists() && xmlFile.length() > 0) {
                return readXml(xmlFile);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Set<Currency> readXml(File file) throws Exception {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);

        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("ValCurs");
        Node nNode = nodeList.item(0);
        Set<Currency> currencies = new TreeSet<>();
        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            NodeList childsList = nNode.getChildNodes();
            for (int i = 0; i < childsList.getLength(); i++) {
                Element eElement = (Element) childsList.item(i);
                Currency currency = new Currency();
                currency.setId(eElement.getAttribute("ID"));
                currency.setNumcode(eElement.getElementsByTagName("NumCode").item(0).getTextContent());
                currency.setCharcode(eElement.getElementsByTagName("CharCode").item(0).getTextContent());
                currency.setNominal(Integer.parseInt(eElement.getElementsByTagName("Nominal").item(0).getTextContent()));
                currency.setName(eElement.getElementsByTagName("Name").item(0).getTextContent());
                currency.setCourse(Double.parseDouble(eElement.getElementsByTagName("Value").item(0).getTextContent().replaceAll(",", ".")));
                currency.setDate(new Date());
                currencies.add(currency);

            }
        }

        //На странице центробанка нет значения для рубля
        Currency rub = new Currency();

        rub.setId("R00001"); //Придуманное число
        rub.setNumcode("001"); //Придуманное число
        rub.setName("Российский рубль");
        rub.setCharcode("RUB");
        rub.setNominal(1);
        rub.setCourse(1.00);
        rub.setDate(new Date());
        currencies.add(rub);

        return currencies;
    }
}
