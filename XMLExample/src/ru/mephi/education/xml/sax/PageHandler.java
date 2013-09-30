package ru.mephi.education.xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class PageHandler extends DefaultHandler {

	boolean title = false;

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		if (qName.equalsIgnoreCase("title")) {
			title = true;
		}

	}

	@Override
	public void characters(char ch[], int start, int length)
			throws SAXException {

		if (title) {
			String titleString = new String(ch, start, length);
			if (titleString.toUpperCase().contains(
					"Java".toUpperCase())) {
				System.out.println(titleString);
			}
			title = false;
		}

	};

}
