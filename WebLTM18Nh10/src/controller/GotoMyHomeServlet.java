package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BO.CheckLoginBO;
import model.Bean.Account;

@WebServlet("/GotoMyHomeServlet")
public class GotoMyHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GotoMyHomeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//2
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		//3, 3'
		Account a = CheckLoginBO.checkAccount(id,pw);
		
		//4,4'
		if (a==null) {
			response.sendRedirect("Login.jsp");
		} else {
			request.getSession().setAttribute("account", a);
			response.sendRedirect("MyHome.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
