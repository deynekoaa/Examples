package ru.mephi.educaton.servlet.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import ru.mephi.educaton.servlet.db.pojo.Test;

/**
 * Servlet implementation class HibernateExample
 */
@WebServlet("/HibernateExample")
public class HibernateExample extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HibernateExample() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Configuration conf = new Configuration();
		conf.addAnnotatedClass(Test.class);
		conf.configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
				.applySettings(conf.getProperties()).buildServiceRegistry();
		SessionFactory sf = conf.buildSessionFactory(serviceRegistry);
		Session session = sf.openSession();
		Test test;
		String action = request.getParameter("action");
		Transaction ts = null;
		try {
			switch (action) {
			case "select":
				List<Test> list = session.createQuery("from Test").list();
				for (Iterator iterator = list.iterator(); iterator.hasNext();) {
					test = (Test) iterator.next();
					response.getWriter().print(test.getId() + " : ");
					response.getWriter().println(test.getName());
				}
				break;
			case "insert":
				test = new Test();
				test.setId(Integer.parseInt(request.getParameter("key")));
				test.setName(request.getParameter("value"));
				session.persist(test);
				break;
			case "delete":
				test = new Test();
				test.setId(Integer.parseInt(request.getParameter("key")));
				session.delete(test);
				break;
			case "update":
				test = new Test();
				test.setId(Integer.parseInt(request.getParameter("key")));
				test.setName(request.getParameter("value"));
				session.update(test);
				break;
			case "transaction":
				ts = session.beginTransaction();
				test = new Test();
				test.setId(1);
				test.setName("hello");
				session.save(test);
				test = new Test();
				test.setId(2);
				test.setName("world");
				session.save(test);
				ts.commit();
			}
		} catch (Throwable t) {
			if (ts != null) {
				ts.rollback();
			}
			throw new ServletException(t);
		} finally {
			session.flush();
			session.close();
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
