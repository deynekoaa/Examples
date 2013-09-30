package ru.mephi.education.xml.sax;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.helpers.DefaultHandler;

public class SaxParser {

	public static void main(String argv[]) {

		try {

			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();

			DefaultHandler handler = new PageHandler();

			saxParser.parse("/largeXml.xml", handler);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}