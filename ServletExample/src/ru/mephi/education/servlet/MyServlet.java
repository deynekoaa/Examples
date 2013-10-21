package ru.mephi.education.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.xml.sax.SAXException;

import ru.mephi.education.xml.dom.DomWriter;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Get request ammount from session & increment it
		Integer requestAmmount = (Integer) request.getSession().getAttribute(
				"requestAmmount");
		if (requestAmmount == null) {
			requestAmmount = 1;
		} else {
			requestAmmount++;
		}
		// set new request ammount attribute
		request.getSession().setAttribute("requestAmmount", requestAmmount);
		//get opCode request parametr
		String opCode = request.getParameter("opCode");
		switch (opCode) {
		case "hello":
			//hello world resp
			response.getWriter().println("Hello World!!!");
			break;
		case "params":
			//show all params
			Enumeration<String> paramNames = request.getParameterNames();
			while (paramNames.hasMoreElements()) {
				String paramName = paramNames.nextElement();
				response.getWriter().println(
						paramName + "=" + request.getParameter(paramName));
			}
			break;
		case "session":
			//show request ammount
			response.getWriter().println("Request ammount = " + requestAmmount);
			break;
		case "redirect":
			//redirect to google
			response.sendRedirect("http://google.com");
			break;
		case "xml":
			//writ xml to output stream
			try {
				DomWriter.writeXmlToStream(response.getOutputStream());
			} catch (ParserConfigurationException | SAXException
					| TransformerFactoryConfigurationError
					| TransformerException e) {
				throw new ServletException(e);
			}
			break;
		default:
			//set resonse error code & msg
			response.sendError(418, "wrong opCode");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
