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
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import ru.mephi.educaton.servlet.db.pojo.Test;

/**
 * Servlet implementation class StatmentExample
 */
@WebServlet("/StatmentExample")
public class StatmentExample extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StatmentExample() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			DataSource ds = InitialContext
					.doLookup("java:jboss/datasources/ExampleDS");
			Connection conn = null;
			try {
				conn = ds.getConnection();
				conn.setAutoCommit(false);
				String action = request.getParameter("action");
				PreparedStatement pst;
				switch (action) {
				case "select":
					Statement st = conn.createStatement();
					ResultSet rs = st
							.executeQuery("SELECT * FROM TEST ORDER BY ID");
					while (rs.next()) {
						response.getWriter().print(rs.getInt(1) + " : ");
						response.getWriter().println(rs.getString(2));
					}
					break;
				case "insert":
					pst = conn
							.prepareStatement("INSERT INTO TEST VALUES(?, ?);");
					pst.setInt(1, Integer.parseInt(request.getParameter("key")));
					pst.setString(2, request.getParameter("value"));
					pst.execute();
					pst.close();
					break;
				case "delete":
					pst = conn.prepareStatement("DELETE FROM TEST WHERE ID=?;");
					pst.setInt(1, Integer.parseInt(request.getParameter("key")));
					pst.execute();
					pst.close();
					break;
				case "update":
					pst = conn
							.prepareStatement("UPDATE TEST SET NAME=? WHERE ID=?;");
					pst.setInt(2, Integer.parseInt(request.getParameter("key")));
					pst.setString(1, request.getParameter("value"));
					pst.execute();
					pst.close();
					break;
				case "transaction":
					pst = conn
							.prepareStatement("INSERT INTO TEST VALUES(?, ?);");
					pst.setInt(1, 1);
					pst.setString(2, "Hello");
					pst.execute();
					pst.setInt(1, 2);
					pst.setString(2, "World!!!");
					pst.execute();
					pst.close();
				}
			} catch (SQLException e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					throw new ServletException(e1);
				}
				throw new ServletException(e);
			} finally {
				if (conn != null) {
					try {
						conn.commit();
						conn.close();
					} catch (SQLException e) {
						throw new ServletException(e);
					}
				}
			}
		} catch (NamingException e) {
			throw new ServletException(e);
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
