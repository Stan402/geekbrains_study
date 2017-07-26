package ru.geekbrains.java3.big_test;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TestBDtoXML {

    private static DataBaseManager dataBaseManager;
    private static Map<NaturalKey, String> data;
    private static String fileName = "test.xml";
    private static String syncFile = "test1.xml";


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
//        try {
//            writeDataToXML(file);
//        } catch (ParserConfigurationException | SAXException | IOException e) {
//            e.printStackTrace();
//        }
        File sfile = new File(syncFile);
        try {
            Map<NaturalKey, String> dataFromFile = parceXML(sfile);
            syncData(dataFromFile);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }


        //testBDtoXML.dataBaseManager.initDB();
    }

    private static void syncData(Map<NaturalKey, String> dataFromFile){
         Map<NaturalKey, String> dataToAdd = new HashMap<>();
         Map<NaturalKey, String> dataToUpdate = new HashMap<>();
         Set<NaturalKey> dataToDelete = new HashSet<>();

        for (Map.Entry<NaturalKey, String> entry:data.entrySet()) {
            if (!dataFromFile.containsKey(entry.getKey())) dataToDelete.add(entry.getKey());
        }
        for (Map.Entry<NaturalKey, String> entry:dataFromFile.entrySet()) {
            if (!data.containsKey(entry.getKey())) dataToAdd.put(entry.getKey(), entry.getValue());
            else if (!entry.getValue().equals(data.get(entry.getKey()))) dataToUpdate.put(entry.getKey(), entry.getValue());
        }

        dataBaseManager.updateDB(dataToDelete, dataToAdd, dataToUpdate);

    }

    private static Map<NaturalKey, String> parceXML(File syncFile) throws ParserConfigurationException, IOException, SAXException {
        Map<NaturalKey, String> result = new HashMap<>();
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse(syncFile);
        Node root = document.getDocumentElement();
        NodeList lines = root.getChildNodes();
        for (int i = 0; i < lines.getLength(); i++) {
            Node line = lines.item(i);
            NodeList fields = line.getChildNodes();
            String depCode = fields.item(0).getTextContent();
            String depJob = fields.item(1).getTextContent();
            String descrptn = fields.item(2).getTextContent();
            if(depCode.length() > 20 || depJob.length() > 100 || descrptn.length() > 255){
                System.out.println("wrong field size in " + i + " line");
                return null;
            }
            NaturalKey key = new NaturalKey(depCode, depJob);
            if (result.containsKey(key)) throw new DoubleNaturalKeyException();
            result.put(key, descrptn);
        }
        return result;
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
            fos.close();
        } catch (TransformerException | IOException e) {
            e.printStackTrace();
        }


    }
}
