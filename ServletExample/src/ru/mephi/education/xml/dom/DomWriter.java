package ru.mephi.education.xml.dom;

import java.io.IOException;
import java.io.OutputStream;

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

	public static void writeXmlToStream(OutputStream os)
			throws ParserConfigurationException, SAXException, IOException,
			TransformerConfigurationException,
			TransformerFactoryConfigurationError, TransformerException {
		Document doc = createDocument();

		addContent(doc);

		saveContentToStream(doc, os);

	}

	private static void saveContentToStream(Document doc, OutputStream os)
			throws TransformerException {
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(
				"{http://xml.apache.org/xslt}indent-amount", "3");
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(os);
		transformer.transform(source, result);
	}

	private static void addContent(Document doc) {
		Element elem = doc.createElement("staff");
		doc.appendChild(elem);
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

	private static Document createDocument()
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.newDocument();
		return doc;
	}

}
