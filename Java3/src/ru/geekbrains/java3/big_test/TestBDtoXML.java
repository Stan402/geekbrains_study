package ru.geekbrains.java3.big_test;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestBDtoXML {

    private static DataBaseManager dataBaseManager;
    private static Map<NaturalKey, String> data;
    private static String fileName = "test.xml";


    public static void main(String[] args) {
        String dbpath = "BigTestSQL.db";

        dataBaseManager = new DataBaseManager(dbpath);
        data = dataBaseManager.loadDB();
//        for (Map.Entry<NaturalKey, String> entry: data.entrySet()) {
//            System.out.println(entry.getKey()+" " + entry.getValue());
//        }
        File file = new File(fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writeDataToXML(file);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        //testBDtoXML.dataBaseManager.initDB();
    }

    private static void writeDataToXML(File file) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        Element root = document.createElement("DBCopy");
        document.appendChild(root);

        for (Map.Entry<NaturalKey, String> entry: data.entrySet()) {
            addNewEntry(document, entry);
        }
        writeDocument(document);
    }

    private static void addNewEntry(Document document, Map.Entry<NaturalKey, String> entry){
        Node root = document.getDocumentElement();
        Element line = document.createElement("Line");
        Element depCode = document.createElement("DepCode");
        depCode.setTextContent(entry.getKey().getDepCode());
        Element depJob = document.createElement("DepJob");
        depJob.setTextContent(entry.getKey().getDepJob());
        Element description = document.createElement("Description");
        description.setTextContent(entry.getValue());
        line.appendChild(depCode);
        line.appendChild(depJob);
        line.appendChild(description);
        root.appendChild(line);
    }

    private static void writeDocument(Document document)  {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream(fileName);
            StreamResult result = new StreamResult(fos);
            tr.transform(source, result);
        } catch (TransformerException | IOException e) {
            e.printStackTrace();
        }
    }
}
