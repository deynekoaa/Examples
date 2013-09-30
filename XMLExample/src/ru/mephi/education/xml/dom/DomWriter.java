package ru.mephi.education.xml.dom;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class DomWriter {

	private static File outFile = new File("new_staff.xml");

	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException, TransformerException {

		Document doc = parseFile();

		addContent(doc);

		saveContent(doc);
	}

	private static void saveContent(Document doc)
			throws TransformerFactoryConfigurationError,
			TransformerConfigurationException, TransformerException {
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(
				"{http://xml.apache.org/xslt}indent-amount", "3");
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(outFile);
		transformer.transform(source, result);
	}

	private static void addContent(Document doc) {
		Element elem = doc.createElement("staff");
		doc.getDocumentElement().appendChild(elem);
		elem.setAttribute("id", "3001");
		Element firstNameElem = doc.createElement("firstname");
		elem.appendChild(firstNameElem);
		firstNameElem.setTextContent("Alexandr");
		Element lastNameElem = doc.createElement("lastname");
		elem.appendChild(lastNameElem);
		lastNameElem.setTextContent("Boruchinkin");
		Element nickNameElem = doc.createElement("nickname");
		elem.appendChild(nickNameElem);
		nickNameElem.setTextContent("Boruch");
		Element salaryElem = doc.createElement("salary");
		elem.appendChild(salaryElem);
		salaryElem.setTextContent("300000");

		doc.normalize();
	}

	private static Document parseFile() throws ParserConfigurationException,
			SAXException, IOException {
		File fXmlFile = new File("staff.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
		return doc;
	}

}
